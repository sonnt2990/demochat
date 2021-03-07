package com.zitga.chat.server.model;

import com.zitga.chat.base.constant.MessageConstant;
import com.zitga.utils.TimeUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

@Entity(value = MessageConstant.MESSAGE_COLLECTION, noClassnameStored = true)
public class MessageModel {

    @Id
    private ObjectId id;

    @Property("from")
    private String senderPerson;

    @Property("to")
    private String receiverPerson;

    @Property("created_time")
    private Date dateTime;

    @Property("message")
    private String message;


    public MessageModel(String senderPerson, String receiverPerson, String message) {
        id = new ObjectId();
        this.senderPerson = senderPerson;
        this.receiverPerson = receiverPerson;
        this.message = message;
        dateTime = TimeUtils.getCurrentTimeInGMT();
    }

    public MessageModel(ObjectId id, String senderPerson, String receiverPerson, Date dateTime, String message) {
        this.id = id;
        this.senderPerson = senderPerson;
        this.receiverPerson = receiverPerson;
        this.dateTime = dateTime;
        this.message = message;
    }

    public String getSenderPerson() {
        return senderPerson;
    }

    public String getReceiverPerson() {
        return receiverPerson;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }
}
