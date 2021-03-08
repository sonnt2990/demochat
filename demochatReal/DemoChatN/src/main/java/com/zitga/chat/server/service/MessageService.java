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
public class MessageService implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private MessageHistoryDAO messageHistoryDAO;

    @BeanMethod
    private void init() {
        logger.info("Initializing MessageService ...");
    }
    public List<String> findAllService(){
        return messageHistoryDAO.findAll();
    }

    public MessageModel messageSaveService(MessageModel messageModel){
        return messageHistoryDAO.messageSave(messageModel);
    }
    @Override
    public void run() {
        logger.info("Run in MessageService");
    }
}
