package com.hdi.hdi.common.CustomException;

public interface CommonError {
    public int getErrorCode();

    public String getErrorMsg();

    public CommonError setErrorMsg(String ErrorMsg);
}
