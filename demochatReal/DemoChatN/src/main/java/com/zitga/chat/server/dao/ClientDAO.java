package com.zitga.chat.server.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.config.DbConfig;
import com.zitga.chat.server.model.ClientModel;
import org.mongodb.morphia.Datastore;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class ClientDAO {
    @BeanField
    private DbConfig dbConfig;

    public List<ClientModel> findAll() {
        DBCollection m = dbConfig.getDatastore().getCollection(ClientModel.class);
        List clientId = m.distinct("id", new BasicDBObject());
        return clientId;
    }

    public void clientSave(ClientModel clientModel) {
        dbConfig.getDatastore().save(clientModel);
    }

}
