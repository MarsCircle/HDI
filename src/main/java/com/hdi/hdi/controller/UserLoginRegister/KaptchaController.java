//package com.hdi.hdi.controller.UserLoginRegister;
//
//
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//import com.hdi.hdi.common.ServerResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//
//@RestController
//@RequestMapping("/api/kaptcha")
//public class KaptchaController {
//
//    @Autowired
//    DefaultKaptcha defaultKaptcha;
//
//    @GetMapping("/defaultKaptcha")
//    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
//            throws Exception {
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            // 生产验证码字符串并保存到session中
//            String createText = defaultKaptcha.createText();
//            httpServletRequest.getSession().setAttribute("verificationCode", createText);
//            // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }
//}
//
//
//    @PostMapping("/checkVerificationCode")
//public ServerResponse<String> checkVerificationCode(@RequestParam(value = "verificationCode") String verificationCode, HttpServletRequest httpServletRequest) {
//    String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute("verificationCode");
//    httpServletRequest.getSession().removeAttribute("verificationCode");
//    if (StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(verificationCode)) {
//        return ServerResponse.createByErrorMessage("验证码错误，或已失效");
//    }
//    return ServerResponse.createBySuccess();
//}
//}