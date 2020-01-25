//package com.hdi.hdi.util;
//
//
//import com.hdi.hdi.common.Const;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//
//public class SendEmail {
//
////    private static Session getSession() {
//////        Properties props = new Properties();
//////        props.put("mail.smtp.host", Const.HOST);//设置服务器地址
//////        props.put("mail.store.protocol", Const.PROTOCOL);//设置协议
//////        props.put("mail.smtp.port", Const.PORT);//设置端口
//////        props.put("mail.smtp.auth", true);
////
////        Authenticator authenticator = new Authenticator() {
////
////            @Override
////            protected PasswordAuthentication getPasswordAuthentication() {
////                return new PasswordAuthentication(Const.FROM,Const.PWD);
////            }
////
////        };
////        Session session = Session.getDefaultInstance(props, authenticator);
////
////        return session;
////    }
//
////    public static void send(String toEmail, String content) {
//////        Session session = getSession();
////        try {
////            System.out.println("--send--" + content);
////            // Instantiate a message
////            Message msg = new MimeMessage(session);
////
////            //Set message attributes
////            msg.setFrom(new InternetAddress(Const.FROM));
////            InternetAddress[] address = {new InternetAddress(toEmail)};
////            msg.setRecipients(Message.RecipientType.TO, address);
////            msg.setSubject("账号激活邮件");
////            msg.setSentDate(new Date());
////            msg.setContent(content, "text/html;charset=utf-8");
////
////            //Send the message
////            Transport.send(msg);
////        } catch (MessagingException mex) {
////            mex.printStackTrace();
////        }
//
//
//
////    }
//
//
//    @Autowired
//    JavaMailSender jms;
//
//    public static void send(String email , String content) {
//        //建立邮件消息
//
//        SimpleMailMessage mainMessage = new SimpleMailMessage();
//        //发送者
//        mainMessage.setFrom("amazingryan@163.com");
//        //接收者
//        mainMessage.setTo(email);
//        //发送的标题
//        mainMessage.setSubject("这是账号激活的邮件");
//        //发送的内容
//        mainMessage.setText(content);
//        jms.send(mainMessage);
//
//    }
//}