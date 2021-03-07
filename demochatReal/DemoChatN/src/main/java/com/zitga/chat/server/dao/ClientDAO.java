package com.zitga.chat.server.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.config.DbConfig;
import com.zitga.chat.server.model.ClientModel;
import com.zitga.chat.server.model.MessageModel;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class ClientDAO {
    @BeanField
    private DbConfig dbConfig;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<ClientModel> findAll() {
//        Query<ClientModel> allClient = dbConfig.getDatastore().createQuery(ClientModel.class);
//        return allClient.asList(new FindOptions().limit(10));

        DBCollection m = dbConfig.getDatastore().getCollection(ClientModel.class);
        List clientId = m.distinct("id", new BasicDBObject());
        return clientId;

    }

    public ClientModel clientSave(ClientModel clientModel) {
        if (clientModel != null) {
            dbConfig.getDatastore().save(clientModel);
            logger.info("Saved client!!");
            return clientModel;
        } else {
            logger.info("Failed to save client!!");
            return null;
        }
    }

}
