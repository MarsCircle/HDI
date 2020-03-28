package com.hdi.hdi.controller.UserLoginRegister;

import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.emailLimit;
import com.hdi.hdi.util.phonelimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Controller
@RequestMapping("/user/")
public class UserRegisterController {

    @Autowired
    private IUserService iUserService;


    @RequestMapping(value = "registerNormal", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> registerNormal(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        if (phonelimit.checkTelePhoneNumber(phone) || phonelimit.checkPhoneNumber(phone) || phonelimit.checkTelePhoneNumberWithAreaCode(phone)) {
        if(emailLimit.checkEmail(email)){
            throw new TransactionException(EmBusinessError.EMAIL_LIMIT_ERROR);
        }
            return iUserService.registerNormal(verificationCode, username, password, email, phone, occupation, nameChinese, address);
        }else {
            throw new TransactionException(EmBusinessError.PHONE_LIMIT_ERROR);

        }
    }

    @RequestMapping(value = "registerMedical", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> registerMedical(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        if (phonelimit.checkTelePhoneNumber(phone) || phonelimit.checkPhoneNumber(phone) || phonelimit.checkTelePhoneNumberWithAreaCode(phone)) {
            if (emailLimit.checkEmail(email)) {
                throw new TransactionException(EmBusinessError.EMAIL_LIMIT_ERROR);
            }
            return iUserService.registerMedical(verificationCode, username, password, email, phone, occupation, nameChinese, address, company, workPermit);
        }else{
                throw new TransactionException(EmBusinessError.PHONE_LIMIT_ERROR);

            }
    }

    @RequestMapping(value = "registerResearcher", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> registerResearcher(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        if (phonelimit.checkTelePhoneNumber(phone) || phonelimit.checkPhoneNumber(phone) || phonelimit.checkTelePhoneNumberWithAreaCode(phone)) {
            if (emailLimit.checkEmail(email)) {
                throw new TransactionException(EmBusinessError.EMAIL_LIMIT_ERROR);
            }
            return iUserService.registerResearcher(verificationCode, username, password, email, phone, occupation, nameChinese, address, company, workPermit);
        }else {
            throw new TransactionException(EmBusinessError.PHONE_LIMIT_ERROR);

        }
    }



}