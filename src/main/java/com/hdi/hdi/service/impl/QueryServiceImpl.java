package com.hdi.hdi.service.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.*;
import com.hdi.hdi.pojo.*;
import com.hdi.hdi.service.IQueryService;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("iQueryService")
public class QueryServiceImpl implements IQueryService {

    @Autowired
    private FormulaMapper formulaMapper;
    @Autowired
    private HerbMapper herbMapper;
    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private CompoundMapper compoundMapper;
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private HDCompoundMapper hdCompoundMapper;
    @Autowired
    private TargetCompoundMapper targetCompoundMapper;
    @Autowired
    private FormulaHerbMapper formulaHerbMapper;
    @Autowired
    private HDInteractionMapper hdInteractionMapper;



    @Override
    public ServerResponse<Formula> formula(String formulaName, String group, String subGroup) throws TransactionException {
        if(formulaName.isEmpty() || group.isEmpty() || subGroup.isEmpty() ){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }

        Formula formula = formulaMapper.selectFormula(formulaName,group,subGroup);
        if (formula == null){
            throw new TransactionException(EmBusinessError.FORMULA_NOT_EXIST);
        }
        String formulaId = formula.getFormulaId();
        List<Map> formulaCompose = formulaHerbMapper.selectFormulaCompose(formulaId);
        StringBuilder Compose = new StringBuilder();
        for(int i = 0 ; i < formulaCompose.size() ; i++) {
            String herbId = formulaCompose.get(i).get("herb_id").toString();
            String herbName = herbMapper.selectHerbName(herbId);
            Compose.append(herbName).append("(")
                    .append(formulaCompose.get(i).get("herb_quantity"))
                    .append(formulaCompose.get(i).get("herb_quantity_unit"))
                    .append(")、");
        }
        String formulaCompose1=Compose.toString();
        formula.setformulaCompose(formulaCompose1);
        return ServerResponse.createBySuccess("查找成功", formula);
    }


    @Override
    public ServerResponse<Herb> herb(String chineseSimplified, String classChinese) throws TransactionException {

        if(chineseSimplified.isEmpty()||classChinese.isEmpty()){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        Herb herb = herbMapper.selectHerb(chineseSimplified,classChinese);
        if(herb != null) {
            return ServerResponse.createBySuccess("查找成功", herb);
        } else {
            throw new TransactionException(EmBusinessError.HERB_NOT_EXIST);
        }
    }

    @Override
    public void herbAcmpdCompound(HttpServletResponse httpServletResponse, String chineseSimplified, String classChinese , int page) throws TransactionException, IOException, JSONException {
        if(chineseSimplified.isEmpty()||classChinese.isEmpty() || page == 0){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        String herbDrugId = herbMapper.selectHerbId(chineseSimplified,classChinese);
        List<String> acmpdId = hdCompoundMapper.selectAcmpdId(herbDrugId);
        int countTargetCompound = 0;
        int pageCount = 0;
        ServletOutputStream os=httpServletResponse.getOutputStream();
        JSONObject o = new JSONObject(true);//加true Json有顺序,要用Ali包才没有反斜杠
        List<TargetCompound> targetCompoundAll = new ArrayList<>();
        page = (page - 1) * 6;
        for(int i = 0 ; i < acmpdId.size() ; i++) {
            HashMap<String, Integer> countTargetCompounds = countTargetCompounds(acmpdId.get(i));
            List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId.get(i) , page);
            countTargetCompound = countTargetCompound + countTargetCompounds.get("countTargetCompound");
            pageCount = pageCount + countTargetCompounds.get("pageCount");
            List<TargetCompound> targetCompoundNew = getTargetCompounds(acmpdId.get(i), targetCompound);
            targetCompoundAll.addAll(targetCompoundNew);
        }
        if(targetCompoundAll.isEmpty()){
            throw new TransactionException(EmBusinessError.HERBCOMPOUND_NOT_EXIST);
        }
        Object json = JSONObject.toJSON(targetCompoundAll);
        o.put("countTargetCompound", countTargetCompound);
        o.put("pageCount", pageCount);
        o.put("result",json);
        String  str = o.toString();
        byte[] b = str.getBytes();
        os.write(b);
        
    }


    private List<TargetCompound> getTargetCompounds(String acmpdId, List<TargetCompound> targetCompound) {
        for (TargetCompound compound : targetCompound) {
            String targetId = compound.getTargetId();
            String gene_symbol = targetMapper.selectGeneSymbol(targetId);
            String molecule_name = compoundMapper.selectMoleculeName(acmpdId);
            compound.setGeneSymbol(gene_symbol);
            compound.setMoleculeName(molecule_name);
        }
        return targetCompound;
    }


    private HashMap<String, Integer> countTargetCompounds(String acmpdId) {

        int countTargetCompound = targetCompoundMapper.targetCompoundCount(acmpdId);
        int pageCount = countTargetCompound / 6;
        if (pageCount * 6 != countTargetCompound) {
            pageCount = pageCount + 1;
        }
        HashMap<String,Integer> countTargetCompounds = new HashMap<>();
        countTargetCompounds.put("countTargetCompound" , countTargetCompound);
        countTargetCompounds.put("pageCount" , pageCount);
        return countTargetCompounds;

    }

    @Override
    public List<Drug> drug(String drugName, String drugIndication , String route) throws TransactionException {
        if(drugName.isEmpty()||drugIndication.isEmpty() || route.isEmpty()){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        List<Drug> drug = drugMapper.selectDrug(drugName,drugIndication,route);
        if(drug != null) {
            return drug;
        } else {
            throw new TransactionException(EmBusinessError.DRUG_NOT_EXIST);
        }
    }

    @Override
    public void drugAcmpdCompound(HttpServletResponse httpServletResponse,String drugName, String drugIndication , String route,int page) throws TransactionException, IOException {
        if(drugName.isEmpty()||drugIndication.isEmpty() || route.isEmpty()||page==0){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        List<String> herbDrugId = drugMapper.selectDrugId(drugName,drugIndication,route);
        if(herbDrugId.isEmpty()){
            throw new TransactionException(EmBusinessError.DRUG_NOT_EXIST);
        }
        List<String> acmpdId = hdCompoundMapper.selectAcmpdId(herbDrugId.get(0));
        for(int j= 1; j< herbDrugId.size() ;j++){
            acmpdId.addAll( hdCompoundMapper.selectAcmpdId(herbDrugId.get(j)));
        }

        int countTargetCompound = 0;
        int pageCount = 0;
        ServletOutputStream os=httpServletResponse.getOutputStream();
        JSONObject o = new JSONObject(true);//加true Json有顺序,要用Ali包才没有反斜杠
        List<TargetCompound> targetCompoundAll = new ArrayList<>();
        page = (page - 1) * 6;
        for(int i = 0 ; i < acmpdId.size() ; i++) {
            HashMap<String, Integer> countTargetCompounds = countTargetCompounds(acmpdId.get(i));
            List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId.get(i) , page);
            countTargetCompound = countTargetCompound + countTargetCompounds.get("countTargetCompound");
            pageCount = pageCount + countTargetCompounds.get("pageCount");
            List<TargetCompound> targetCompoundNew = getTargetCompounds(acmpdId.get(i), targetCompound);
            targetCompoundAll.addAll(targetCompoundNew);
        }

        if(targetCompoundAll.isEmpty()){
            throw new TransactionException(EmBusinessError.DRUGCOMPOUND_NOT_EXIST);
        }
        Object json = JSONObject.toJSON(targetCompoundAll);
        o.put("countTargetCompound", countTargetCompound);
        o.put("pageCount", pageCount);
        o.put("result",json);
        String  str = o.toString();
        byte[] b = str.getBytes();
        os.write(b);

    }




    @Override
    public ServerResponse<Compound> compound(String moleculeName, String obScore, String moleculeWeight) throws TransactionException {

        if(moleculeName.isEmpty()||obScore.isEmpty() || moleculeWeight.isEmpty()){
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        Compound compound = compoundMapper.selectCompound(moleculeName,obScore,moleculeWeight);
        if( compound ==null) {
            throw new TransactionException(EmBusinessError.COMPOUND_NOT_EXIST);
        }
        String acmpdId = compound.getAcmpdId();
        List<String> herbDrugId = hdCompoundMapper.selectId(acmpdId);
        for(int i = 0 ;i< herbDrugId.size() ;i++){
            String HerbName = herbMapper.selectHerbName(herbDrugId.get(i));
            if(HerbName.isEmpty()){
                String DrugName = drugMapper.selectDrugName(herbDrugId.get(i));
                compound.setDrugName(DrugName);
            }else {
                compound.setHerbName(HerbName);
            }
        }
        return ServerResponse.createBySuccess("查找成功", compound);
    }


    @Override
    public void compoundToAcmpd(HttpServletResponse httpServletResponse ,String moleculeName, String obScore , String moleculeWeight,int page) throws TransactionException, IOException {
        Compound compound = compoundMapper.selectCompound(moleculeName,obScore,moleculeWeight);
        if(compound == null){
            throw new TransactionException(EmBusinessError.COMPOUND_NOT_EXIST);
        }
        String acmpdId = compound.getAcmpdId();
        page = (page -1) * 8;
        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId , page);
        for(int i = 0 ; i < targetCompound.size() ; i++) {
            String targetId = targetCompound.get(i).getTargetId();
            String gene_symbol = targetMapper.selectGeneSymbol(targetId);
            targetCompound.get(i).setGeneSymbol(gene_symbol);
        }
        HashMap<String, Integer> countTargetCompounds = countTargetCompounds(acmpdId);
        ServletOutputStream os= httpServletResponse.getOutputStream();
        JSONObject o = new JSONObject(true);//加true Json有顺序,要用Ali包才没有反斜杠
        int countTargetCompound = countTargetCompounds.get("countTargetCompound");
        int pageCount = countTargetCompounds.get("pageCount");
        Object json = JSONObject.toJSON(targetCompound);
        o.put("countTargetCompound", countTargetCompound);
        o.put("pageCount", pageCount);
        o.put("result",json);
        String  str = o.toString();
        byte[] b = str.getBytes();
        os.write(b);

    }




    @Override
    public ServerResponse<Target> target(String geneSymbol, String species) throws TransactionException {
        Target target = targetMapper.selectTarget(geneSymbol,species);
        if(target ==null){
            throw new TransactionException(EmBusinessError.TARGET_NOT_EXIST);
        }
        StringBuilder herbName = new StringBuilder();
        StringBuilder drugName = new StringBuilder();
        String targetId = target.getTargetId();
        List<String> acmpdId = targetCompoundMapper.selectAcmpdId(targetId);
        for(int i = 0 ; i < acmpdId.size() ; i++) {
            List<String> herbDrugId = hdCompoundMapper.selectId(acmpdId.get(i));
            for (int j = 0; j < herbDrugId.size(); j++) {
                String HerbName = herbMapper.selectHerbName(herbDrugId.get(j));
                if(HerbName==null){
                    String DrugName = drugMapper.selectDrugName(herbDrugId.get(j));
                    drugName.append(DrugName)
                            .append("、");
                }else {
                    herbName.append(HerbName)
                            .append("、");
                }
            }
        }
        if(herbName.length() != 0)
        {herbName.deleteCharAt(herbName.length() - 1);
            target.setHerbName(herbName.toString());
        }
        if(drugName.length() != 0 )
        {drugName.deleteCharAt(drugName.length() - 1);
            target.setDrugName(drugName.toString());}
        if(target.getDrugName()==null){
            target.setDrugName("");
        }
        if(target.getHerbName()==null){
            target.setHerbName("");
        }

        return ServerResponse.createBySuccess("查找成功", target);

    }


    @Override
    public void targetToAcmpd(HttpServletResponse httpServletResponse ,String geneSymbol, String species,int page) throws TransactionException, IOException {
        Target target = targetMapper.selectTarget(geneSymbol,species);
        if(target == null){
            throw new TransactionException(EmBusinessError.TARGET_NOT_EXIST);
        }
        String targetId = target.getTargetId();
        List<String> acmpdId = targetCompoundMapper.selectAcmpdId(targetId);
        List<Map<String,String>> herbDrugName = new ArrayList<>();
        int countTargetCompound = 0;
        int pageCount = 0;
        for (String s : acmpdId) {
            HashMap<String, Integer> countTargetCompounds = countTargetCompounds(s);
            countTargetCompound = countTargetCompound + countTargetCompounds.get("countTargetCompound");
            pageCount = pageCount + countTargetCompounds.get("pageCount");
            TargetCompound targetCompound = targetCompoundMapper.selectTargetCompoundByAcmpdId(s, targetId);
            List<String> herbDrugId = hdCompoundMapper.selectId(s);
            for (int j = (page - 1) * 8; j < herbDrugId.size() && j < page * 8; j = j + 1) {
                HashMap<String, String> map = new HashMap<>();
                String HerbName = herbMapper.selectHerbName(herbDrugId.get(j));
                if (HerbName==null) {
                    String DrugName = drugMapper.selectDrugName(herbDrugId.get(j));
                    map.put("herbDrugName", DrugName);
                    map.put("MoleculeName", targetCompound.getMoleculeName());
                    map.put("TargetClass", targetCompound.getTargetClass());
                    map.put("Relation", targetCompound.getRelation());
                    map.put("Species", targetCompound.getSpecies());
                    map.put("Reference", targetCompound.getReference());
                    herbDrugName.add(map);
                } else {
                    map.put("herbDrugName", HerbName);
                    map.put("MoleculeName", targetCompound.getMoleculeName());
                    map.put("TargetClass", targetCompound.getTargetClass());
                    map.put("Relation", targetCompound.getRelation());
                    map.put("Species", targetCompound.getSpecies());
                    map.put("Reference", targetCompound.getReference());
                    herbDrugName.add(map);
                }
            }
        }

        ServletOutputStream os= httpServletResponse.getOutputStream();
        JSONObject o = new JSONObject(true);//加true Json有顺序,要用Ali包才没有反斜杠
        Object json = JSONObject.toJSON(herbDrugName);
        o.put("countTargetCompound", countTargetCompound);
        o.put("pageCount", pageCount);
        o.put("result",json);
        String  str = o.toString();
        byte[] b = str.getBytes();
        os.write(b);

    }

    @Override
    public ServerResponse<HDInteraction> hdInteraction(String herbOrFormulaName, String drugName) throws TransactionException {
        //判断西药是否存在
        if(drugMapper.checkDrug(drugName) == 0){
            throw new TransactionException(EmBusinessError.DRUG_NOT_EXIST);

        }
        String drugId = drugMapper.selectDrugIdByName(drugName);
        //判断是方剂还是中药
        if (herbMapper.checkHerb(herbOrFormulaName) != 0) {
            String herbId = herbMapper.selectHerbIdByName(herbOrFormulaName);
            HDInteraction hdInteraction = hdInteractionMapper.selectHDInteraction(herbId,drugId);
            hdInteraction.setDrugName(drugName);
            hdInteraction.setHerbName(herbOrFormulaName);
            return ServerResponse.createBySuccess("查找成功" , hdInteraction);
        } else {
            if (formulaMapper.checkFormula(herbOrFormulaName) != 0) {
                List<Map> herbNameId = formulaMapper.selectHerbNameId(herbOrFormulaName);
                String herbName = (String)herbNameId.get(0).get("herbName");
                String herbId = (String)herbNameId.get(0).get("herbId");
                HDInteraction hdInteraction = hdInteractionMapper.selectHDInteraction(herbId,drugId);
                hdInteraction.setDrugName(drugName);
                hdInteraction.setHerbName(herbName);
                return ServerResponse.createBySuccess("查找成功",hdInteraction);
            }else {
                throw new TransactionException(EmBusinessError.HERB_NOT_EXIST);

            }
        }
    }


}

