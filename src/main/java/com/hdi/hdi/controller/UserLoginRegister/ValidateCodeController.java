package com.hdi.hdi.controller.UserLoginRegister;

import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.emailLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user/")
public class ValidateCodeController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "ValidateCode", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> ValidateCode(String email) throws TransactionException {
        if (emailLimit.checkEmail(email)) {
            throw new TransactionException(EmBusinessError.EMAIL_LIMIT_ERROR);
        }
        if (iUserService.checkEmail(email)) {
            return ServerResponse.createBySuccessMessage("验证码已发送");
        } else {
            throw new TransactionException(EmBusinessError.EMAIL_CODE_ERROR);
        }
    }
}