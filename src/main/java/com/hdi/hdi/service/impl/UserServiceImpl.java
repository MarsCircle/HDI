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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

//import com.hdi.hdi.util.SendEmail;

@Service("iUserService")
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  JavaMailSender mailSender;


    @Override
    public ServerResponse<User> login(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user = userMapper.selectLogin(username);
        if (user.getIsStatus() == (byte) 1) {
            String hash = user.getPassword();
            if (!PasswordHash.validatePassword(password, hash)) { //此处验证是否与加盐hash过的密码一致
                return ServerResponse.createByErrorMessage("密码错误");
            }
            user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登陆成功", user);
        } else {
            return ServerResponse.createByErrorMessage("该账号尚未激活，请前往邮箱进行激活");
        }
    }


    public ServerResponse<String> register(String username, String password, String email, String phone) throws InvalidKeySpecException, NoSuchAlgorithmException {

        User user = new User(username, password, email, phone);

        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        user.setIsStatus((byte) 0);
        String ValidateCode = PasswordHash.createHash(user.getEmail());
        if (checkEmail(user.getEmail(), ValidateCode)) {
//        if (true) { // TODO: 2020/1/25 邮箱激活暂时有点问题，先改成true
            int resultCount = userMapper.insert(user);
            if (resultCount == 0) {
                return ServerResponse.createByErrorMessage("注册失败");
            }
            return ServerResponse.createBySuccessMessage("注册成功，请查看邮箱并激活账号");
        } else {
            return ServerResponse.createByErrorMessage("激活邮箱发送失败，注册失败");
        }
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


    public boolean checkEmail(String email,String ValidateCode) {

        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/user/register?email=");
//        sb.append("<a href=\"https:www.baidu.com");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(ValidateCode);
        sb.append("\">http://localhost:8080/user/register?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(ValidateCode);
        sb.append("</a>");
        //发送邮件
        return send(email, sb.toString());
    }


    ///传递激活码和email过来
    public ServerResponse<String> activateUser(String email , String validateCode) {
        //数据访问层，通过email获取用户信息
        User user = userMapper.selectEmail(email);
        //验证用户是否存在
        if (user != null) {
            //验证用户激活状态
            if (user.getIsStatus() == (byte) 0) {
                ///没激活
                Date currentTime = new Date();//获取当前时间
                //验证链接是否过期
                if (currentTime.before(user.getCreateTime())) {
                    //验证激活码是否正确
                    try {
                        if (PasswordHash.validatePassword(email, validateCode)) {
                            //激活成功， //并更新用户的激活状态，为已激活
                            user.setIsStatus((byte) 1);//把状态改为激活
                            userMapper.activateByEmail(email);
//                            return ServerResponse.createBySuccessMessage("激活成功！");
                        } else {
                            return ServerResponse.createByErrorMessage("激活码不正确");
                        }

                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();
                    }
                } else {
                    return ServerResponse.createByErrorMessage("激活码已过期！");
                }
            } else {
                return ServerResponse.createBySuccessMessage("邮箱已激活，请登录！");
            }
        } else {
            return ServerResponse.createByErrorMessage("该邮箱未注册（邮箱地址不存在）！");
        }
    return ServerResponse.createBySuccessMessage("激活成功！");
    }



    public boolean send(String email , String content) {
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
            helper.setSubject("这是账号激活的邮件");
            helper.setText(content, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}

