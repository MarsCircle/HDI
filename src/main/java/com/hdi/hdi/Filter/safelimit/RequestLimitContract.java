//package com.wecan.demo.safelimit;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.data.redis.core.*;
//import com.github.pagehelper.util.StringUtil;
//
//
//HttpServletRequest request = null;
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = servletRequestAttributes.getResponse();
//@Aspect
//@Component
//public class RequestLimitContract {
//
//    private static final Logger logger = LoggerFactory.getLogger(RequestLimitContract.class);
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//    private static String limitPath="/safeLimit/limit";
//
//    @Before(value = "within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
//    public void requestLimit(final JoinPoint joinPoint , RequestLimit limit) throws RequestLimitException {
//        try {
//            Object[] args = joinPoint.getArgs();//获取参数对象
//            HttpServletRequest request = null;
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//            HttpServletResponse response = servletRequestAttributes.getResponse();
//

package com.hdi.hdi.Filter.safelimit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class RequestLimitContract {

    private static final Logger logger = LoggerFactory.getLogger(RequestLimitContract.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.hdi.hdi.controller..*.*(..))")
    public void WebPointcut(){

    }
    @Before("WebPointcut() && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint , RequestLimit limit) throws RequestLimitException {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            Object[] args = joinPoint.getArgs();//获取参数对象
//            HttpServletRequest request = null;
//            for (int i = 0; i < args.length; i++) {
//                if (args[i] instanceof HttpServletRequest) {
//                    request = (HttpServletRequest) args[i];
//                    break;//instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例 此处循环选出HttpServletRequest
//                }
//            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }

            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            long count = redisTemplate.opsForValue().increment(key, 1);
            System.out.println(count);
            if (count == 1) {
                redisTemplate.expire(key, limit.time(), TimeUnit.MILLISECONDS);
            }
            if (count > limit.count()) {
                logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;

        }
    }
}

