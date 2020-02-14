package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.Const;
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

//import com.hdi.hdi.util.SendEmail;

@Service("iUserService")
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  JavaMailSender mailSender;


    @Override
    public ServerResponse<User> login(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        int resultCount = userMapper.checkEmail(email);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该邮箱未注册");
        }
        User user = userMapper.selectLogin(email);
        String hash = user.getPassword();
        if (!PasswordHash.validatePassword(password, hash)) { //此处验证是否与加盐hash过的密码一致
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);

    }


    public ServerResponse<String> register(String verificationCode , String username, String password, String email, String phone, String occupation,String nameChinese ,String address,String company) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //判断参数是否为空值
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)  || StringUtils.isEmpty(phone) ||  StringUtils.isEmpty(occupation) ||StringUtils.isEmpty(company) ) {
            return ServerResponse.createByErrorMessage("参数不可为空");
        }
        //判断邮箱验证码是否有误
        Jedis jedis = new Jedis();
        String verificationCodeRight = jedis.get(email + "_verificationCode");
        if(verificationCodeRight == null){
            return ServerResponse.createByErrorMessage("邮箱验证码已过期");
        }
        if(!verificationCode.equals(verificationCodeRight)){
            return ServerResponse.createByErrorMessage("邮箱验证码有误！注册失败");
        }
        User user = new User(username, password, email, phone, occupation, nameChinese, address, company);
        //判断邮箱是否已经注册
        ServerResponse validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        //判断用户名是否已存在
//        validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
//        if (!validResponse.isSuccess()) {
//            return validResponse;
//        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
        }



    public ServerResponse<String> checkValid(String str , String type){
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type))
        {
            if (Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("该邮箱已被注册");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }



    public boolean checkEmail(String email) {

        StringBuilder ValidateCode = new StringBuilder();
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        for (int i = 0; i <6; i++){
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





    public boolean sendEmail(String email , String content) {
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
            return false;
        }
    }
}

