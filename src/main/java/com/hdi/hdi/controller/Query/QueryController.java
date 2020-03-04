package com.hdi.hdi.controller.Query;

import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.*;
import com.hdi.hdi.service.IQueryService;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.JWT.CookieUtil;
import com.hdi.hdi.util.JWT.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;


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
    public ServerResponse<Formula> formula(String formulaName , String group ,String subGroup) {
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
    //TODO : 这里参数先用是中文的，之后还得补上其它语言的
    public ServerResponse<Herb> herb(String chineseSimplified , String classChinese ) {
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
    public List<TargetCompound> herbAcmpdCompound(String chineseSimplified,String classChinese ,int page ) {
        return iQueryService.herbAcmpdCompound( chineseSimplified  , classChinese ,page);
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
    public ServerResponse<Drug> drug(String drugName, String drugIndication , String route) {
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
    public List<TargetCompound> drugAcmpdCompound(String drugName, String drugIndication , String route,int page) {
        return iQueryService.drugAcmpdCompound( drugName  , drugIndication ,route,page);
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
    public ServerResponse<Compound> compound(String moleculeName, String obScore , String moleculeWeight) {
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
    public List<TargetCompound> compoundToAcmpd(String moleculeName, String obScore , String moleculeWeight ,int page) {
        return iQueryService.compoundToAcmpd( moleculeName  , obScore ,moleculeWeight,page);
    }


    /**
     * Target查询
     * @param geneSymbol
     * @param species
     * @return
     */
    @RequestMapping(value = "target",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Target> target(String geneSymbol, String species ) {
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
    public List<Map<String,String>>  targetToAcmpd(String geneSymbol, String species ,int page) {
        return iQueryService.targetToAcmpd( geneSymbol  , species ,page);
    }

    /**
     * HDCompound查询  由方剂或中药名字 与 西药名字查相互作用
     * @param herbOrFormulaName
     * @param drugName
     * @return ServerResponse<HDCompound>
     */
    @RequestMapping(value = "hdInteraction",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<HDInteraction>  hdInteraction(String herbOrFormulaName, String drugName ) {
        return iQueryService.hdInteraction( herbOrFormulaName  , drugName );
    }
}

