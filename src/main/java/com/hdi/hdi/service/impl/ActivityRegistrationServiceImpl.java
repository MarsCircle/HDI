package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.Const;
import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.ActivityMapper;
import com.hdi.hdi.dao.ActivityRegistrationMapper;
import com.hdi.hdi.dao.ContactUsMapper;
import com.hdi.hdi.pojo.ActivityRegistration;
import com.hdi.hdi.pojo.ContactUs;
import com.hdi.hdi.service.IActivityRegistrationService;
import com.hdi.hdi.util.JWT.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("iActivityRegistrationService")
public class ActivityRegistrationServiceImpl implements IActivityRegistrationService {


    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public boolean ActivityRegistration(HttpServletRequest httpServletRequest, String title, String registrationDeadline, String limitNumber) throws TransactionException {
        String token = httpServletRequest.getHeader(Const.JWT_TOKEN_NAME);
        if (token == null) {
            throw new TransactionException(EmBusinessError.USER_NOT_LOGIN);
        }
        if (title.isEmpty() || limitNumber.isEmpty() || registrationDeadline.isEmpty()) {
            throw new TransactionException(EmBusinessError.PARAMETER_NULL_ERROR);
        }
        //对比截止日期
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        int result = registrationDeadline.compareTo(dateString);
        if (result < 0) {
            throw new TransactionException(EmBusinessError.REGISTRATION_TIME_EXPIRED);
        }
        String subject = JwtUtil.parseToken(httpServletRequest, Const.JWT_TOKEN_NAME, Const.SIGNINGKEY);
        if (subject == null) {
            throw new TransactionException(EmBusinessError.LOGIN_OUTOFDATE);
        }
        Long activityId = activityMapper.selectByTitle(title);
        if (activityId == null) {
            throw new TransactionException(EmBusinessError.ACTIVITY_NOT_EXIST);
        }
        int registerNumber = activityRegistrationMapper.countRegisterNumber(activityId);
        if (registerNumber == Integer.parseInt(limitNumber)) {
            throw new TransactionException(EmBusinessError.ACTIVITY_REGISTER_IS_FULL);
        }

        ActivityRegistration activityRegistration = new ActivityRegistration(activityId, title, subject);
        if (activityRegistrationMapper.activityRegistration(activityRegistration) > 0) {
            return true;
        } else {
            throw new TransactionException(EmBusinessError.ACTIVITY_REGISTER_IS_FULL);
        }
    }
}


