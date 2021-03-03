package com.zitgacore.chat.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private static MasterConnectorService masterConnectorService;
    
    
    public static void start() throws Exception {
    	
        try {
        	
            logger.info("-----------------------------------------------------------------");
            logger.info("Server starting...");
            
            
            masterConnectorService = new MasterConnectorService();
            masterConnectorService.run();

            logger.info("Server start successfully!");
            logger.info("-----------------------------------------------------------------");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

}
