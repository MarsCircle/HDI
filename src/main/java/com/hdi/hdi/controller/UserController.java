package com.hdi.hdi.controller;


import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;
import com.hdi.hdi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 登录注册
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<User> login(String username , String password , HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ServerResponse<User> response = iUserService.login(username , password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER , response.getData());
        }
        return response;
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str , String type){
        return iUserService.checkValid(str ,type);
    }
}
