package com.hdi.hdi.Filter.safelimit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("safeLimit")
public class RequestLimitExpController {


    @RequestMapping(value = "/limit")
    @ResponseBody
    public Map<String, Object> safeLimitRes(HttpServletRequest request, Integer pagenum, Integer pagesize){
        Map<String, Object> outputMap =new HashMap<String,Object>();
        outputMap.put("success", false);
        outputMap.put("msg", "HTTP请求超出设定的限制");
        return outputMap;
    }

}
