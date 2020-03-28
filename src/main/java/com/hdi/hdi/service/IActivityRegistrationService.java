package com.hdi.hdi.service;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.ActivityRegistration;
import com.hdi.hdi.pojo.Formula;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IActivityRegistrationService {

    boolean ActivityRegistration(HttpServletRequest httpServletRequest , String title , String registrationDeadline, String limitNumber) throws TransactionException;

}
