package com.zitga.chat.client;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;

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
