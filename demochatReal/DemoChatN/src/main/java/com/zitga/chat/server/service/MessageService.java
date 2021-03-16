package com.zitga.chat.server.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.chat.server.dao.MessageHistoryDAO;
import com.zitga.chat.server.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class MessageService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private MessageHistoryDAO messageHistoryDAO;

    public List<String> findAllService(){
        return messageHistoryDAO.findAll();
    }
    public void messageSaveService(MessageModel messageModel){
        messageHistoryDAO.messageSave(messageModel);
    }
}
