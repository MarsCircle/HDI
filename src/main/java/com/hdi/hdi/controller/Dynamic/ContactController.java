package com.hdi.hdi.controller.Dynamic;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.ContactUs;
import com.hdi.hdi.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/contact/")
public class ContactController {

    @Autowired
    private IContactService iContactService;

    /**
     * 联系我们
     * @param type
     * @param occupation
     * @param phone
     * @param email
     * @param content
     * @return
     */
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<String> contact(String type , String occupation,String phone , String email , String content) throws TransactionException {
        ServerResponse<String> contact = iContactService.contact( type , occupation , phone,email, content);
        return contact;
    }



}