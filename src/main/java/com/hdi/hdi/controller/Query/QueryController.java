package com.hdi.hdi.controller.Query;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.*;
import com.hdi.hdi.service.IQueryService;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/query/")
public class QueryController {

    @Autowired
    private IQueryService iQueryService;


    /**
         * formular查询
         * @param formulaName
         * @param group
         * @param subGroup
         * @return
         */
    @RequestMapping(value = "formula",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<Formula> formula(String formulaName , String group ,String subGroup) throws TransactionException {
        return iQueryService.formula( formulaName , group , subGroup );
        }


    /**
     * herb查询
     * @param chineseSimplified
     * @param classChinese
     * @return
     */
    @RequestMapping(value = "herb",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Herb> herb(String chineseSimplified , String classChinese ) throws TransactionException {
        return iQueryService.herb( chineseSimplified , classChinese );
    }

    /**
     * herbAcmpdCompound查询
     * @param chineseSimplified
     * @param classChinese
     * @param page
     * @return
     */
    @RequestMapping(value = "herbAcmpdCompound",method = RequestMethod.POST)
    @ResponseBody
    public void herbAcmpdCompound(HttpServletResponse httpServletResponse , String chineseSimplified, String classChinese , int page ) throws TransactionException, IOException, JSONException {
        iQueryService.herbAcmpdCompound(httpServletResponse, chineseSimplified  , classChinese ,page);
    }

    /**
     * drug查询
     * @param drugName
     * @param drugIndication
     * @param route
     * @return
     */
    @RequestMapping(value = "drug",method = RequestMethod.POST)
    @ResponseBody
    public List<Drug> drug(String drugName, String drugIndication , String route) throws TransactionException {
        return iQueryService.drug( drugName , drugIndication ,route);
    }

    /**
     * drugAcmpdCompound查询
     * @param drugName
     * @param drugIndication
     * @param route
     * @param page
     * @return
     */
    @RequestMapping(value = "drugAcmpdCompound",method = RequestMethod.POST)
    @ResponseBody
    public void drugAcmpdCompound(HttpServletResponse httpServletResponse,String drugName, String drugIndication , String route,int page) throws TransactionException, IOException {
        iQueryService.drugAcmpdCompound(httpServletResponse, drugName  , drugIndication ,route,page);
    }


    /**
     * Compound查询
     * @param moleculeName
     * @param obScore
     * @param moleculeWeight
     * @return
     */
    @RequestMapping(value = "compound",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Compound> compound(String moleculeName, String obScore , String moleculeWeight) throws TransactionException {
        return iQueryService.compound( moleculeName  , obScore ,moleculeWeight);
    }

    /**
     * CompoundToAcmpd查询
     * @param moleculeName
     * @param obScore
     * @param moleculeWeight
     * @param page
     * @return
     */
    @RequestMapping(value = "compoundToAcmpd",method = RequestMethod.POST)
    @ResponseBody
    public void compoundToAcmpd(HttpServletResponse httpServletResponse ,String moleculeName, String obScore , String moleculeWeight ,int page) throws TransactionException, IOException {
         iQueryService.compoundToAcmpd( httpServletResponse,moleculeName  , obScore ,moleculeWeight,page);
    }


    /**
     * Target查询
     * @param geneSymbol
     * @param species
     * @return
     */
    @RequestMapping(value = "target",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Target> target(String geneSymbol, String species ) throws TransactionException {
        return iQueryService.target( geneSymbol  , species );
    }

    /**
     * TargetToAcmpd查询
     * @param geneSymbol
     * @param species
     * @param page
     * @return
     */
    @RequestMapping(value = "targetToAcmpd",method = RequestMethod.POST)
    @ResponseBody
    public void  targetToAcmpd(HttpServletResponse httpServletResponse , String geneSymbol, String species ,int page) throws TransactionException, IOException {
        iQueryService.targetToAcmpd( httpServletResponse, geneSymbol  , species ,page);
    }

    /**
     * HDCompound查询  由方剂或中药名字 与 西药名字查相互作用
     * @param herbOrFormulaName
     * @param drugName
     * @return ServerResponse<HDCompound>
     */
    @RequestMapping(value = "hdInteraction",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<HDInteraction>  hdInteraction(String herbOrFormulaName, String drugName ) throws TransactionException {
        return iQueryService.hdInteraction( herbOrFormulaName  , drugName );
    }
}

