package com.hdi.hdi.controller.UserLoginRegister;


import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.JWT.CookieUtil;
import com.hdi.hdi.util.JWT.JwtUtil;
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



/**
 * 登录注册
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String SIGNINGKEY = "signingKey";
    @Autowired
    private IUserService iUserService;
//    /**
//     * 用户登录  带图形验证码的登录
//     * @param username
//     * @param password
//     * @param session
//     * @return
//     */
//    @RequestMapping(value = "login",method = RequestMethod.POST)
//    @ResponseBody //序列化为json
//    public ServerResponse<User> login(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse, String username , String password , String verificationCode, HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute("verificationCode");
//        httpServletRequest.getSession().removeAttribute("verificationCode");
//        if (StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(verificationCode)) {
//            return ServerResponse.createByErrorMessage("验证码错误，或已失效");
//        }
//
//        ServerResponse<User> response = iUserService.login(username , password);
//        if(response.isSuccess()){
//            session.setAttribute(Const.CURRENT_USER , response.getData());
//        }
//
//        String token = JwtUtil.generateToken(SIGNINGKEY, username);
//        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
//        return response;
//    }



        /**
          * 用户登录  带图形验证码的登录
          * @param email
          * @param password
          * @param session
          * @return
          */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<User> login( HttpServletResponse httpServletResponse, String email , String password , HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ServerResponse<User> response = iUserService.login(email , password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER , response.getData());
        }

        String token = JwtUtil.generateToken(SIGNINGKEY, email);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        return response;
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpServletResponse httpServletResponse,HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(String verificationCode, String username, String password, String email, String phone,String occupation,String nameChinese ,String address,String company) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return iUserService.register(verificationCode, username, password, email, phone,occupation,nameChinese,address,company);
    }


    @RequestMapping(value = "ValidateCode",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> ValidateCode(String email) {
         if(iUserService.checkEmail(email)){
             return ServerResponse.createBySuccessMessage("验证码已发送");
         }else{
             return ServerResponse.createByErrorMessage("验证码发送失败");
         }
    }

//    @RequestMapping(value = "activateUser",method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse<String> activateUser(String email , String validateCode)  {
//        return iUserService.activateUser(email,validateCode);
//    }


    @RequestMapping(value = "checkValid",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str , String type){
        return iUserService.checkValid(str ,type);
    }

}
