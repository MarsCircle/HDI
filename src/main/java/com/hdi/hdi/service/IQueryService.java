package com.hdi.hdi.service;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

public interface IQueryService {

    ServerResponse<Formula> formula(String formulaName , String group ,String subGroup) ;

    ServerResponse<Herb> herb(String chineseSimplified, String classChinese);

    List<TargetCompound> herbAcmpdCompound(String chineseSimplified, String classChinese , int page);

    ServerResponse<Drug> drug(String drugName, String drugIndication , String route);

    List<TargetCompound> drugAcmpdCompound(String drugName, String drugIndication , String route,int page);

    ServerResponse<Compound> compound(String moleculeName, String obScore, String moleculeWeight);

    List<TargetCompound> compoundToAcmpd(String moleculeName, String obScore, String moleculeWeight, int page);

    ServerResponse<Target> target(String geneSymbol, String species);

    List<Map<String,String>>  targetToAcmpd(String geneSymbol, String species, int page);
}
