//package com.hdi.hdi.Filter;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class ValidateCodeFilter extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if(StringUtils.equals("/user/login",request.getRequestURI())
//        && StringUtils.equalsIgnoreCase(request.getMethod(),"post") ){
//            try{
//                validate(new ServletWebRequest(request));
//            }catch (Exception e){
//                //todo
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
//    private void validate(ServletWebRequest servletWebRequest){
//
//    }
//}
