package com.hdi.hdi.service.impl;

import com.hdi.hdi.common.CustomException.EmBusinessError;
import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.dao.*;
import com.hdi.hdi.pojo.ContactUs;
import com.hdi.hdi.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iContactService")
public class ContactServiceImpl implements IContactService {


    @Autowired
    private ContactUsMapper contactUsMapper;


    @Override
    public ServerResponse<String> contact(String type, String occupation, String phone, String email, String content) throws TransactionException {

        ContactUs contactUs = new ContactUs(type, occupation, phone, email, content);
        if (contactUsMapper.insert(contactUs) == 1) {
            return ServerResponse.createBySuccess("已收到您的反馈");
        } else {
            throw new TransactionException(EmBusinessError.CONTACT_ERROR);
        }
    }
}




