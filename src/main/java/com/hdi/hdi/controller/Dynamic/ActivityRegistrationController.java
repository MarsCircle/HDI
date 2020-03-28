package com.hdi.hdi.controller.Dynamic;

import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.service.IActivityRegistrationService;
import com.hdi.hdi.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;


@Controller
@RequestMapping("/activity/")
public class ActivityRegistrationController {

    @Autowired
    private IActivityRegistrationService iActivityRegistrationService;

    /**
     * 活动报名
     * @param httpServletRequest
     * @param title
     * @param registrationDeadline
     * @param limitNumber
     * @return ServerResponse<String>
     */
    @RequestMapping(value = "registration",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<String> activityRegistration(HttpServletRequest httpServletRequest , String title , String registrationDeadline, String limitNumber ) throws TransactionException {

        if (iActivityRegistrationService.ActivityRegistration(httpServletRequest, title, registrationDeadline, limitNumber)) {
            return ServerResponse.createBySuccessMessage("已成功报名");
        } else {
            throw new TransactionException(EmBusinessError.ACTIVITY_REGISTER_FALSE);
        }

    }
}