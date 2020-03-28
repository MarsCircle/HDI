package com.hdi.hdi.service;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.*;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IQueryService {

    ServerResponse<Formula> formula(String formulaName , String group ,String subGroup) throws TransactionException;

    ServerResponse<Herb> herb(String chineseSimplified, String classChinese) throws TransactionException;

    void herbAcmpdCompound(HttpServletResponse httpServletResponse, String chineseSimplified, String classChinese , int page) throws TransactionException, IOException, JSONException;

    List<Drug> drug(String drugName, String drugIndication , String route) throws TransactionException;

    void drugAcmpdCompound(HttpServletResponse httpServletResponse,String drugName, String drugIndication , String route,int page) throws TransactionException, IOException;

    ServerResponse<Compound> compound(String moleculeName, String obScore, String moleculeWeight) throws TransactionException;

    void compoundToAcmpd(HttpServletResponse httpServletResponse ,String moleculeName, String obScore, String moleculeWeight, int page) throws TransactionException, IOException;

    ServerResponse<Target> target(String geneSymbol, String species) throws TransactionException;

    void  targetToAcmpd(HttpServletResponse httpServletResponse ,String geneSymbol, String species, int page) throws TransactionException, IOException;

    ServerResponse<HDInteraction> hdInteraction(String herbOrFormulaName, String drugName) throws TransactionException;




}
