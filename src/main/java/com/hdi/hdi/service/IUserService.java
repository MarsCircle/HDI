package com.hdi.hdi.service;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {

   ServerResponse<User> login(String email , String password) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException;
   ServerResponse<User> loginJWT(String email) throws InvalidKeySpecException, NoSuchAlgorithmException;

   ServerResponse<String> registerNormal(String verificationCode,String username, String password, String email, String phone,String occupation,String nameChinese ,String address) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException;

   ServerResponse<String> checkValid(String str,String type) throws TransactionException;


   boolean checkEmail(String email) throws TransactionException;

   boolean sendEmail(String email , String content) throws TransactionException;

   ServerResponse<String> registerMedical(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException;

   ServerResponse<String> registerResearcher(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException;
}
