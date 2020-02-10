package com.hdi.hdi.service;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface IQueryService {

    ServerResponse<Formula> formula(String formulaName , String group ,String subGroup) ;

    ServerResponse<Herb> herb(String chineseSimplified, String classChinese);

    List<TargetCompound> herbAcmpdCompound(String chineseSimplified, String classChinese , int page);

    ServerResponse<Drug> drug(String drugName, String drugIndication , String route);

    List<TargetCompound> drugAcmpdCompound(String drugName, String drugIndication , String route,int page);
}
