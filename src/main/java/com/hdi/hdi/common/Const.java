package com.hdi.hdi.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email" ;

    public static final String USERNAME = "username";


    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_MEDICAL = 1; //医疗用户
        int ROLE_RESEARCHER = 2; // 研究人员

    }

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int SALT_BYTE_SIZE = 24;
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 1000;
    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

//    private static final String jwtTokenCookieName = "JWT-TOKEN";

    public static final String JWT_TOKEN_NAME = "JWT-TOKEN";

    public static final String SIGNINGKEY = "signingKey";


    



}
