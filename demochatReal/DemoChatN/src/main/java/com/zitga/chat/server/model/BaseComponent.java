package com.zitga.chat.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Transient;

public abstract class BaseComponent {
    @JsonIgnore
    @Id
    private String id;

    @JsonIgnore
    @Transient
    @NotSaved
    private ClientModel client;

    @JsonIgnore
    @Transient
    @NotSaved
    private MessageModel message;

    public BaseComponent() {
    }
    public BaseComponent(ClientModel client) {
        this.id=client.getId();
    }

    public BaseComponent( MessageModel message) {
        this.id= message.getSenderPerson();
    }

    @JsonIgnore
    public void bindingWithMessage(MessageModel message) {
        this.message = message;
    }
    @JsonIgnore
    public void bindingWithPlayer(ClientModel client) {
        this.client = client;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public ClientModel getClient() {
        return client;
    }

    public MessageModel getMessage() {
        return message;
    }
}
