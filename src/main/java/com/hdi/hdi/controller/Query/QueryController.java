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
        ServerResponse<Formula> response = iQueryService.formula( formulaName , group , subGroup );
        return response;
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
        ServerResponse<Herb> response = iQueryService.herb( chineseSimplified , classChinese );
        return response;
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
        List<TargetCompound> targetCompound = iQueryService.herbAcmpdCompound( chineseSimplified  , classChinese ,page);
        return targetCompound;
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
        ServerResponse<Drug> response = iQueryService.drug( drugName , drugIndication ,route);
        return response;
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
        List<TargetCompound> targetCompound = iQueryService.drugAcmpdCompound( drugName  , drugIndication ,route,page);
        return targetCompound;
    }



}

