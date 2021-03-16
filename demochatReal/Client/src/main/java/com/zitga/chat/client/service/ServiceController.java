package com.zitga.chat.client.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;

@BeanComponent
public class ServiceController {

    private static ServiceController instance;

    public static ServiceController instance() {
        return instance;
    }

    @BeanField
    private GetMessageService getMessageService;

    public ServiceController() {
        instance = this;
    }

    public void init() {
        getMessageService.test();
    }
}
