package com.zitga.chat.server.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.config.DbConfig;
import com.zitga.chat.server.model.MessageModel;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@BeanComponent
public class MessageHistoryDAO {
    @BeanField
    private DbConfig dbConfig;

    public List<String> findAll() {
        DBCollection messageObject = dbConfig.getDatastore().getCollection(MessageModel.class);
        DBCursor cursors = messageObject.find().sort(new BasicDBObject()).limit(100);
        List<String> messages = new ArrayList<>();
        for (DBObject dbObject : cursors) {
            messages.add(dbObject.get("message").toString());
        }
        return messages;
    }
    public void messageSave(MessageModel messageModel) {
        dbConfig.getDatastore().save(messageModel);
    }
}
