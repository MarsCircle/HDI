//package com.hdi.hdi.Filter;
//
//import com.hdi.hdi.util.JWT.JwtUtil;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Order(1)
//@WebFilter(filterName = "loginFilter", urlPatterns = "/user/login")
//public class JwtFilter extends OncePerRequestFilter {
//    private static final String jwtTokenCookieName = "JWT-TOKEN";
//    private static final String signingKey = "signingKey";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String email = JwtUtil.parseToken(httpServletRequest, jwtTokenCookieName, signingKey);
//        if(email == null){
////            String authService = this.getFilterConfig().getInitParameter("services.auth");
//            httpServletResponse.sendRedirect("?redirect=" + httpServletRequest.getRequestURL());
//        } else{
//            httpServletRequest.setAttribute("email", email);
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//    }
//}