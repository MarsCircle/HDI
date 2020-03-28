package com.hdi.hdi.service;

import com.hdi.hdi.common.CustomException.TransactionException;
import com.hdi.hdi.common.ServerResponse;
import com.hdi.hdi.pojo.ContactUs;

public interface IContactService {

    ServerResponse<String> contact(String type , String occupation, String phone , String email , String content) throws TransactionException;
}
