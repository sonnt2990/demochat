package com.zitgacore.chat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

public class MainClient {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(MainClient.class);
        try {
            final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
            logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());
            Bootstrap.start();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.out.print(logger.toString());
            throw new RuntimeException(e);
        }

    }
}
