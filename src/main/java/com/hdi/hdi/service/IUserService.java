package com.hdi.hdi.service;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {

   ServerResponse<User> login(String username , String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> register(String verificationCode,String username, String password, String email, String phone,String occupation,String nameChinese ,String address,String company) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> checkValid(String str,String type);


   boolean checkEmail(String email);

   boolean sendEmail(String email , String content);

}
