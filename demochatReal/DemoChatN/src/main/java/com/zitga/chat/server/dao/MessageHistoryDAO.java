package com.zitga.chat.server.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.config.DbConfig;
import com.zitga.chat.server.model.MessageModel;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class MessageHistoryDAO {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @BeanField
    private DbConfig dbConfig;

    public List<MessageModel> findAll() {

//        Query<MessageModel> allMessages = dbConfig.getDatastore().createQuery(MessageModel.class);
//        allMessages.order(Sort.descending("created_time"));
//        return allMessages.asList(new FindOptions().limit(100));
        DBCollection m = dbConfig.getDatastore().getCollection(MessageModel.class);
        List messages = m.distinct("message", new BasicDBObject());
        return messages;
    }

    public List<MessageModel> findMessage(int id) {
        Query<MessageModel> query = dbConfig.getDatastore().createQuery(MessageModel.class);
        query.field("from").equal(id);
        query.order(Sort.descending("created_time"));
        return query.asList(new FindOptions().limit(100));
    }

    public List<MessageModel> findMessageByPerson(int senderId, int receiver) {
        Query<MessageModel> query = dbConfig.getDatastore().createQuery(MessageModel.class);
//        query.field(DatabaseConstant.ID_TAG).equal(id);
        query.field("from").equals(senderId);
        query.field("to").equals(receiver);
        query.order(Sort.descending("created_time"));

        return query.asList(new FindOptions().limit(100));
    }

    public MessageModel messageSave(MessageModel messageModel) {
        if (messageModel != null) {
            dbConfig.getDatastore().save(messageModel);
            logger.info("Saved message!!");
            return messageModel;
        } else {
            logger.info("Failed to message!!");
            return null;
        }
    }
}
