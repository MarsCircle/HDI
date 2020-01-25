package com.hdi.hdi.service;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {

   ServerResponse<User> login(String username , String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> register(String username, String password, String email, String phone) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> checkValid(String str,String type);

   ServerResponse<String> activateUser(String email , String validateCode);

   boolean checkEmail(String email,String ValidateCode);

   boolean send(String email , String content);
}
