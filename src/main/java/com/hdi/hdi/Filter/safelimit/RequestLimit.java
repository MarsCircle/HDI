package com.hdi.hdi.Filter.safelimit;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 类说明:自定义注解限制访问时间长度最多访问次数
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期，自定义注解一般都用RUNTIME
@Target(ElementType.METHOD)//用于描述注解的使用范围、只适用于方法。注意这个的范围
@Documented//一个类型声明被注释了文档化，它的注释成为公共API的一部分
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {

    /**
     * 允许访问的最大次数
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}
