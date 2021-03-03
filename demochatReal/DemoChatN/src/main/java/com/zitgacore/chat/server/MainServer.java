package com.zitgacore.chat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import com.zitga.core.ServerBootstrap;

public class MainServer {
	public static void main(String[] args) throws Exception {
   	 Logger logger = LoggerFactory.getLogger(MainServer.class);
        try {
            final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
            logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());

            ServerBootstrap.start();

        } catch (Exception e) {

            ServerBootstrap.stop();

            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

   }
}
