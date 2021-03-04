package com.zitga.chat.server;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import com.zitga.core.ServerBootstrap;

public class MainServer {
	public static void main(String[] args) throws Exception {
        Configurator.initialize("idle-summoner", "config/log4j.properties");
   	 Logger logger = LoggerFactory.getLogger(MainServer.class);
        try {
            final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
            logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());

            ServerBootstrap.start();

        } catch (Exception e) {

        	logger.error(e.getMessage(), e);
        	
            ServerBootstrap.stop();      
            
            throw new RuntimeException(e);
        }

   }
}
