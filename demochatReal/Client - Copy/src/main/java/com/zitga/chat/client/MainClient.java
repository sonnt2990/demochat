package com.zitga.chat.client;

import com.zitga.chat.client.service.ServiceController;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

public class MainClient {

    public static void main(String[] args) throws Exception {
        Configurator.initialize("client", "config/log4j.properties");
        Logger logger = LoggerFactory.getLogger(MainClient.class);
        try {
            final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
            logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());

            Bootstrap.start();
            ServiceController.instance().init();


        } catch (Exception e) {

            Bootstrap.stop();

            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }
}
