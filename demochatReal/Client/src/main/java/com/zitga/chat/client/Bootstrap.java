package com.zitga.chat.client;

import com.zitga.core.Server;
import com.zitga.core.ServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bootstrap extends ServerBootstrap {

    private static Logger logger = LoggerFactory.getLogger(ServerBootstrap.class);

    private static Server server;

    public static void start() throws Exception {
        try {
            logger.info("-----------------------------------------------------------------");
            logger.info("Client starting...");

            server = new Server();
            server.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                stop();
            }));

            logger.info("Client start successfully!");
            logger.info("-----------------------------------------------------------------");

        } catch (Exception e) {
            stop();
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public static void stop() {
        try {
            logger.info("-----------------------------------------------------------------");
            logger.info("Client stopping...");

            server.stop();

            logger.info("Client stop successfully!");
            logger.info("-----------------------------------------------------------------");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


}
