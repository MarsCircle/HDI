package com.hdi.hdi.controller.UserLoginRegister;

import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserLogoutController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpServletResponse httpServletResponse, HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
//        CookieUtil.clear(httpServletResponse, Const.JWT_TOKEN_NAME);
        return ServerResponse.createBySuccess();
    }
}
