package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.*;
import com.hdi.hdi.pojo.*;
import com.hdi.hdi.service.IQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ServerResponse<Formula> formula(String formulaName, String group, String subGroup) {
        int resultCount = formulaMapper.checkFormula(formulaName);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该方剂不存在");
        }
        Formula formula = formulaMapper.selectFormula(formulaName,group,subGroup);
        List<Map> formulaCompose = formulaHerbMapper.selectFormulaCompose(formulaName);
        StringBuilder Compose = new StringBuilder();
        for(int i = 0 ; i < formulaCompose.size() ; i++) {
            Compose.append(formulaCompose.get(i).get("herb_name")).append("(")
                    .append(formulaCompose.get(i).get("herb_quantity"))
                    .append(formulaCompose.get(i).get("herb_quantity_unit"))
                    .append(")、");
        }
        String formulaCompose1=Compose.toString();
        formula.setformulaCompose(formulaCompose1);
        return ServerResponse.createBySuccess("查找成功", formula);
    }


    @Override
    public ServerResponse<Herb> herb(String chineseSimplified, String classChinese) {
        int resultCount = herbMapper.checkHerb(chineseSimplified);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该中药不存在");
        }
        Herb herb = herbMapper.selectHerb(chineseSimplified,classChinese);
        if(herb != null) {
            return ServerResponse.createBySuccess("查找成功", herb);
        } else {
            return ServerResponse.createByErrorMessage("查找失败");
        }
    }

    @Override
    public List<TargetCompound> herbAcmpdCompound(String chineseSimplified, String classChinese ,int page) {
        String herbId = herbMapper.selectHerbId(chineseSimplified,classChinese);
        String acmpdId = hdCompoundMapper.selectAcmpdId(herbId);
        page = (page -1) * 6;
        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId , page);
        for(int i = 0 ; i < targetCompound.size() ; i++) {
            String targetId = targetCompound.get(i).getTargetId();
            String gene_symbol = targetMapper.selectGeneSymbol(targetId);
            String molecule_name = compoundMapper.selectMoleculeName(acmpdId);
            targetCompound.get(i).setGeneSymbol(gene_symbol);
            targetCompound.get(i).setMoleculeName(molecule_name);
        }


        return targetCompound;
    }


    @Override
    public ServerResponse<Drug> drug(String drugName, String drugIndication , String route) {
        int resultCount = drugMapper.checkDrug(drugName);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该药品不存在");
        }
        Drug drug = drugMapper.selectDrug(drugName,drugIndication,route);
        if(drug != null) {
            return ServerResponse.createBySuccess("查找成功", drug);
        } else {
            return ServerResponse.createByErrorMessage("查找失败");
        }
    }

    @Override
    public List<TargetCompound> drugAcmpdCompound(String drugName, String drugIndication , String route,int page) {
        String drugId = drugMapper.selectDrugId(drugName,drugIndication,route);
        String acmpdId = hdCompoundMapper.selectAcmpdIdByDrugId(drugId);
        page = (page -1) * 6;
        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId , page);
        for(int i = 0 ; i < targetCompound.size() ; i++) {
            String targetId = targetCompound.get(i).getTargetId();
            String gene_symbol = targetMapper.selectGeneSymbol(targetId);
            String molecule_name = compoundMapper.selectMoleculeName(acmpdId);
            targetCompound.get(i).setGeneSymbol(gene_symbol);
            targetCompound.get(i).setMoleculeName(molecule_name);
        }
        return targetCompound;
    }


    @Override
    public ServerResponse<Compound> compound(String moleculeName, String obScore, String moleculeWeight){
        int resultCount = compoundMapper.checkCompound(moleculeName);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该分子不存在");
        }
        Compound compound = compoundMapper.selectCompound(moleculeName,obScore,moleculeWeight);
        String acmpdId = compound.getAcmpdId();
        List<Map> herbDrugId = hdCompoundMapper.selectId(acmpdId);
        String herbName = herbMapper.selectHerbName((String) herbDrugId.get(0).get("herb_id"));
        String drugName = drugMapper.selectDrugName((String) herbDrugId.get(0).get("drug_id"));
        compound.setDrugName(drugName);
        compound.setHerbName(herbName);
        return ServerResponse.createBySuccess("查找成功", compound);

    }


    @Override
    public List<TargetCompound> compoundToAcmpd(String moleculeName, String obScore , String moleculeWeight,int page) {
        Compound compound = compoundMapper.selectCompound(moleculeName,obScore,moleculeWeight);
        String acmpdId = compound.getAcmpdId();
        page = (page -1) * 8;
        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundListByAcmpdId(acmpdId , page);
        for(int i = 0 ; i < targetCompound.size() ; i++) {
            String targetId = targetCompound.get(i).getTargetId();
            String gene_symbol = targetMapper.selectGeneSymbol(targetId);
            targetCompound.get(i).setGeneSymbol(gene_symbol);
        }
        return targetCompound;
    }



    @Override
    public ServerResponse<Target> target(String geneSymbol, String species){
        int resultCount = targetMapper.checkTarget(geneSymbol);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该基因不存在");
        }
        List<Target> target = targetMapper.selectTarget(geneSymbol,species);
        StringBuilder herbName = new StringBuilder();
        StringBuilder drugName = new StringBuilder();

        for(int i = 0 ; i < target.size() ; i++) {
            String acmpdId = target.get(i).getAcmpdId();
            List<Map> herbDrugId = hdCompoundMapper.selectId(acmpdId);
            for (int j = 0; j < herbDrugId.size(); j++) {
                System.out.println(herbDrugId.size());
                herbName.append(herbMapper.selectHerbName((String) herbDrugId.get(j).get("herb_id")))
                        .append("、");
                drugName.append(drugMapper.selectDrugName((String) herbDrugId.get(j).get("drug_id")))
                        .append("、");
            }
        }
        herbName.deleteCharAt(herbName.length() - 1);
        drugName.deleteCharAt(drugName.length() - 1);
        target.get(0).setDrugName(drugName.toString());
        target.get(0).setHerbName(herbName.toString());
        return ServerResponse.createBySuccess("查找成功", target.get(0));

    }


    @Override
    public List<Map<String,String>> targetToAcmpd(String geneSymbol, String species,int page) {
        int resultCount = targetMapper.checkTarget(geneSymbol);
        if (resultCount == 0) {
            return null;
        }
        List<Target> target = targetMapper.selectTarget(geneSymbol,species);
        List<Map<String,String>> herbDrugName = new ArrayList<Map<String, String>>();
        for(int i = 0 ; i < target.size() ; i++) {
            String acmpdId = target.get(i).getAcmpdId();
            String targetId = target.get(i).getTargetId();
            TargetCompound targetCompound = targetCompoundMapper.selectTargetCompoundByAcmpdId(acmpdId,targetId);
            List<Map> herbDrugId = hdCompoundMapper.selectId(acmpdId);
            for (int j = (page -1) * 8; j < herbDrugId.size() && j < page * 8 ; j = j+2) {
                Map<String, String> map = new HashMap<>();
                map.put("herbDrugName" ,herbMapper.selectHerbName((String) herbDrugId.get(j).get("herb_id")));
                map.put("MoleculeName",targetCompound.getMoleculeName());
                map.put("TargetClass",targetCompound.getTargetClass());
                map.put("Relation",targetCompound.getRelation());
                map.put("Species",targetCompound.getSpecies());
                map.put("Reference",targetCompound.getReference());
                herbDrugName.add(map);
                Map<String, String> map1 = new HashMap<>();
                map1.put("herbDrugName" ,drugMapper.selectDrugName((String) herbDrugId.get(j).get("drug_id")));
                map1.put("MoleculeName",targetCompound.getMoleculeName());
                map1.put("TargetClass",targetCompound.getTargetClass());
                map1.put("Relation",targetCompound.getRelation());
                map1.put("Species",targetCompound.getSpecies());
                map1.put("Reference",targetCompound.getReference());
                herbDrugName.add(map1);
//
            }
        }
        return herbDrugName;
    }

    @Override
    public ServerResponse<HDInteraction> hdInteraction(String herbOrFormulaName, String drugName) {
        //判断西药是否存在
        if(drugMapper.checkDrug(drugName) == 0){
            return ServerResponse.createByErrorMessage("无此西药");
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
                return ServerResponse.createByErrorMessage("无此方剂或中药");
            }
        }
    }
}

