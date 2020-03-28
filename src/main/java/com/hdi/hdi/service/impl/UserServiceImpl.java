package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.UserMapper;
import com.hdi.hdi.pojo.User;
import com.hdi.hdi.service.IUserService;
import com.hdi.hdi.util.SaltHash.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Random;


@Service("iUserService")
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  JavaMailSender mailSender;


    @Override
    public ServerResponse<User> login(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        int resultCount = userMapper.checkEmail(email);
        if (resultCount == 0) {
            throw new TransactionException(EmBusinessError.EMAIL_NOT_REGISTER);
        }
        User user = userMapper.selectLogin(email);
        String hash = user.getPassword();
        if (!PasswordHash.validatePassword(password, hash)) { //此处验证是否与加盐hash过的密码一致
            throw new TransactionException(EmBusinessError.USER_NOT_MATCH);
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);

    }

    @Override
    public ServerResponse<User> loginJWT(String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = userMapper.selectLogin(email);
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);

    }

    @Override
    public ServerResponse<String> registerNormal(String verificationCode , String username, String password, String email, String phone, String occupation,String nameChinese ,String address) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        //判断参数是否为空值
        if (StringUtils.isEmpty(verificationCode) ||StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)  || StringUtils.isEmpty(phone) ||  StringUtils.isEmpty(occupation)  ) {
            throw new TransactionException(EmBusinessError.REGISTER_NULL_ERROR);
        }
        checkVerificationCode(email,verificationCode);
        User user = new User(username, password, email, phone, occupation, nameChinese, address);
        //判断邮箱是否已经注册
        ServerResponse validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            throw new TransactionException(EmBusinessError.REGISTER_ERROR);
        }
        return ServerResponse.createBySuccessMessage("注册成功");
        }


    public ServerResponse<String> registerMedical(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        //判断参数是否为空值
        if (StringUtils.isEmpty(verificationCode) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)  || StringUtils.isEmpty(phone) ||  StringUtils.isEmpty(occupation) ||  StringUtils.isEmpty(company)||  StringUtils.isEmpty(workPermit)  ) {
            throw new TransactionException(EmBusinessError.REGISTER_NULL_ERROR);
        }
        checkVerificationCode(email,verificationCode);
        User user = new User(username, password, email, phone, occupation, nameChinese, address, company,  workPermit);
        //判断邮箱是否已经注册
        ServerResponse validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_MEDICAL);
        //MD5加密
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            throw new TransactionException(EmBusinessError.REGISTER_ERROR);
        }
        return ServerResponse.createBySuccessMessage("注册请求已发送，核实中");
    }


    public ServerResponse<String> registerResearcher(String verificationCode, String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) throws InvalidKeySpecException, NoSuchAlgorithmException, TransactionException {
        //判断参数是否为空值
        if (StringUtils.isEmpty(verificationCode) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)  || StringUtils.isEmpty(phone) ||  StringUtils.isEmpty(occupation) ||  StringUtils.isEmpty(company)||  StringUtils.isEmpty(workPermit)  ) {
            throw new TransactionException(EmBusinessError.REGISTER_NULL_ERROR);
        }
        checkVerificationCode(email,verificationCode);
        User user = new User(username, password, email, phone, occupation, nameChinese, address, company,  workPermit);

        //判断邮箱是否已经注册
        ServerResponse validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_RESEARCHER);
        //MD5加密
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            throw new TransactionException(EmBusinessError.REGISTER_ERROR);
        }
        return ServerResponse.createBySuccessMessage("注册请求已发送，核实中");
    }


    public ServerResponse<String> checkVerificationCode(String email , String verificationCode) throws TransactionException {
        //判断邮箱验证码是否有误
        Jedis jedis = new Jedis();
        String verificationCodeRight = jedis.get(email + "_verificationCode");
        if(verificationCodeRight == null){
            throw new TransactionException(EmBusinessError.VERIFICATIONCODE_OUT_OF_DATE);
        }
        if(!verificationCode.equals(verificationCodeRight)){
            throw new TransactionException(EmBusinessError.VERIFICATIONCODE_ERROR);
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse<String> checkValid(String str , String type) throws TransactionException {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type))
        {
            if (Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0 ){
                    throw new TransactionException(EmBusinessError.EMAIL_REGISTER_EXIST);
                }
            }
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }



    public boolean checkEmail(String email) throws TransactionException {
        if(email == null){
            throw new TransactionException(EmBusinessError.EMAIL_NULL_ERROR);
        }
        StringBuilder ValidateCode = new StringBuilder();
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            char num = ch[random.nextInt(ch.length)];
            ValidateCode.append(num);
        }
        Jedis jedis = new Jedis();
        jedis.setex(email + "_verificationCode", 300, ValidateCode.toString());

        //发送邮件
        String content = "注册HDI 的邮箱注册码如下" +
                "邮箱：" +
                email +
                "                验证码:" +
                ValidateCode.toString() ;

        return sendEmail(email, content);
    }





    public boolean sendEmail(String email , String content) throws TransactionException {
        //建立邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
//        SimpleMailMessage mainMessage = new SimpleMailMessage();
//        //发送者
//        mainMessage.setFrom("amazingryan@163.com");
//        //接收者
//        mainMessage.setTo(email);
//        //发送的标题
//        mainMessage.setSubject("这是账号激活的邮件");
//        //发送的内容
//        mainMessage.setText(content);
//        mailSender.send(mainMessage);

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("amazingryan@163.com");
            helper.setTo(email);
//            helper.setTo(to);
            helper.setSubject("HDI邮箱注册码");
            helper.setText(content, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            throw new TransactionException(EmBusinessError.EMAIL_CODE_ERROR);
        }
    }

}

