package com.zitga.chat.server.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.dao.ClientDAO;
import com.zitga.chat.server.model.ClientModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class ClientService implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private ClientDAO clientDAO;

    public void clientSave(ClientModel clientModel){
         clientDAO.clientSave(clientModel);
    }
    public List<ClientModel> allFind(){
        return clientDAO.findAll();
    }

    @Override
    public void run() {
        logger.info("Run in ClientService");
    }
}
