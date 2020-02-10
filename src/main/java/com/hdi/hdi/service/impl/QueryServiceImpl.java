package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.*;
import com.hdi.hdi.pojo.*;
import com.hdi.hdi.service.IQueryService;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.SaltHash.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundByAcmpdId(acmpdId , page);


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

        List<TargetCompound> targetCompound = targetCompoundMapper.selectTargetCompoundByAcmpdId(acmpdId , page);


        for(int i = 0 ; i < targetCompound.size() ; i++) {

            String targetId = targetCompound.get(i).getTargetId();

            String gene_symbol = targetMapper.selectGeneSymbol(targetId);

            String molecule_name = compoundMapper.selectMoleculeName(acmpdId);

            targetCompound.get(i).setGeneSymbol(gene_symbol);

            targetCompound.get(i).setMoleculeName(molecule_name);
        }


        return targetCompound;
    }
}
