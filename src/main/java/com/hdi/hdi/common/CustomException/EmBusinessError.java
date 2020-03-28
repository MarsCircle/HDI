package com.hdi.hdi.common.CustomException;

import com.hdi.hdi.common.CustomException.CommonError;

public enum EmBusinessError implements CommonError {

    EMAIL_NULL_ERROR(20001,"所输入邮箱不可为空"),
    PASSWORD_NULL_ERROR(20002,"所输入密码不可为空"),
    USER_NOT_MATCH(20003,"用户名和密码不匹配"),
    EMAIL_NOT_REGISTER(20004,"该邮箱仍未注册"),
    REGISTER_NULL_ERROR (20005,"部分注册信息不可为空"),
    EMAIL_REGISTER_EXIST(20006,"该邮箱已被注册"),
    REGISTER_ERROR(20007,"注册失败"),
    VERIFICATIONCODE_NULL(20008,"邮箱验证码不可为空"),
    VERIFICATIONCODE_OUT_OF_DATE(20009,"邮箱验证码已过期"),
    VERIFICATIONCODE_ERROR(20010,"邮箱验证码有误"),
    EMAIL_CODE_ERROR(20011,"邮箱验证码发送失败"),
    CONTACT_ERROR(20012,"反馈失败"),
    FORMULA_NOT_EXIST(20013,"该方剂不存在"),
    HERB_NOT_EXIST(20014,"该中药不存在"),
    PARAMETER_NULL_ERROR(20015,"查找信息参数有空值"),
    DRUG_NOT_EXIST(20016,"该西药不存在"),
    COMPOUND_NOT_EXIST(20017,"该分子不存在"),
    TARGET_NOT_EXIST(20018,"该靶标不存在"),
    PHONE_LIMIT_ERROR(20019,"所输入电话格式有误"),
    EMAIL_LIMIT_ERROR(20020,"所输入邮箱格式有误"),
    HERBCOMPOUND_NOT_EXIST(20021, "该中药无相关分子资料"),
    DRUGCOMPOUND_NOT_EXIST(20022, "该西药无相关分子资料"),
    USER_NOT_LOGIN(20023, "该用户尚未登录"),
    REGISTRATION_TIME_EXPIRED(20024, "报名截止时间已过"),
    LOGIN_OUTOFDATE(20025, "登录认证已过期，请重新登录"),
    ACTIVITY_NOT_EXIST(20026, "不存在该活动"),
    ACTIVITY_REGISTER_IS_FULL(20027 , "报名人数已满，报名失败"),
    ACTIVITY_REGISTER_FALSE(20028 , "报名失败")


    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode,String errMsg ) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }



    @Override
    public int getErrorCode() {
        return this.errCode;
    }


    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }


    @Override
    public CommonError setErrorMsg(String ErrorMsg) {
        this.errMsg = ErrorMsg;
        return this;
    }

}