package com.hdi.hdi.service;

import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {

   ServerResponse<User> login(String email , String password) throws InvalidKeySpecException, NoSuchAlgorithmException;
   ServerResponse<User> loginJWT(String email) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> registerNormal(String verificationCode,String username, String password, String email, String phone,String occupation,String nameChinese ,String address) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> checkValid(String str,String type);


   boolean checkEmail(String email);

   boolean sendEmail(String email , String content);

   ServerResponse<String> registerMedical(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> registerResearcher(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
