package com.zitga.chat.client;

import com.zitga.bean.annotation.BeanField;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import com.zitga.core.ServerBootstrap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainClient {

	public static void main(String[] args) throws Exception {
		Configurator.initialize("idle-summoner", "config/log4j.properties");
		Logger logger = LoggerFactory.getLogger(MainClient.class);
		try {
			final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
			logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());

			ServerBootstrap.start();
			ServiceController.instance().init();


		} catch (Exception e) {

			ServerBootstrap.stop();

			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}
}
