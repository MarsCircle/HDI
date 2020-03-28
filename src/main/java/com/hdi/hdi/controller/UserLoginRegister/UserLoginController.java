package com.hdi.hdi.controller.UserLoginRegister;

import com.hdi.hdi.common.*;
import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.pojo.User;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.JWT.JwtUtil;
import com.hdi.hdi.util.emailLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@RequestMapping("/user/")
public class UserLoginController {

    @Autowired
    private IUserService iUserService;


    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @param session
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<User> login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String email, String password, HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, TransactionException {
        String subject = JwtUtil.parseToken(httpServletRequest,  Const.JWT_TOKEN_NAME, Const.SIGNINGKEY);
        if (subject != null) {
            return iUserService.loginJWT(subject);
        }
        if (email == null ) {
            throw new TransactionException(EmBusinessError.EMAIL_NULL_ERROR);
        }
        if(emailLimit.checkEmail(email)){
            throw new TransactionException(EmBusinessError.EMAIL_LIMIT_ERROR);
        }
        if( password == null){
            throw new TransactionException(EmBusinessError.PASSWORD_NULL_ERROR);
        }
        ServerResponse<User> response = iUserService.login(email, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        String token = JwtUtil.generateToken(Const.SIGNINGKEY, email);
        httpServletResponse.setHeader("JWT-TOKEN" , token );
//        CookieUtil.create(httpServletResponse, Const.JWT_TOKEN_NAME, token, false, -1, "101.132.34.39");
        return response;
    }
}