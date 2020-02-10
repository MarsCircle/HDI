package com.hdi.hdi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phonelimit {

    public static boolean checkTelePhoneNumber(String phoneNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(1[3-9])\\d{9}$");
            Matcher matcher = regex.matcher(phoneNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    public static boolean checkTelePhoneNumberWithAreaCode(String phoneNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(\\+86\\D1[3-9])\\d{9}$");
            Matcher matcher = regex.matcher(phoneNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile(("^(\\d{2,4}-\\d{7,8})$"));
            Matcher matcher = regex.matcher(phoneNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
