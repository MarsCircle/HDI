package com.hdi.hdi.util;

import java.util.Scanner;


/**
 * 提示用户输入E-mail，然后对邮箱格式进行验证，要求必须包含@和.，并且最后一个.必须在@之后。
 */
public class emailLimit {
    public static boolean checkEmail(String email) {
        {

            if (email.contains("@") && email.contains(".")) {
                if (email.lastIndexOf(".") > email.lastIndexOf("@")) {
                    return false;
                }
                else {
                    return true;
                }
            }else
                return true;
        }
    }
}

