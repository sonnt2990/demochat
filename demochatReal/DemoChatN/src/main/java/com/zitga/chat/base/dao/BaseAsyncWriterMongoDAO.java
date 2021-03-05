package com.zitga.chat.base.dao;

import com.zitga.mongo.repository.AbstractMongoRepository;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public class BaseAsyncWriterMongoDAO<K extends Serializable, V>  extends AbstractMongoRepository<K, V> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseAsyncWriterMongoDAO(Class<V> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }



    // ---------------------------------------- Flush ----------------------------------------
    public void flushSaveEntity(V entity) {
        super.save(entity);
    }
    public void flushSaveEntities(List<V> entities) {
        super.save(entities);
    }


}
