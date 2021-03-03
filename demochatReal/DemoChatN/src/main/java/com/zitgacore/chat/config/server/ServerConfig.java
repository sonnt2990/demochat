package com.zitgacore.chat.config.server;

import com.zitga.bean.annotation.BeanConfiguration;
import com.zitga.core.config.NetworkConfig;
import com.zitga.core.config.ProtocolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanConfiguration("config/server.properties")
public class ServerConfig {
    private final Logger logger = LoggerFactory.getLogger(ServerConfig.class);
    private int bossThreadNumber = 0;
    private int workerThreadNumber = 0;
    private int schedulerThreadNumber = 0;

    private NetworkConfig network;
    private ProtocolConfig tcp;


    public ServerConfig() {
    }


    public int getBossThreadNumber() {
        if (this.bossThreadNumber == 0) {
            this.logger.info("Auto select bossThreadNumber={}", Runtime.getRuntime().availableProcessors() / 2);
            return Runtime.getRuntime().availableProcessors() / 2;
        } else {
            return this.bossThreadNumber;
        }
    }


    public int getWorkerThreadNumber() {
        if (this.workerThreadNumber == 0) {
            this.logger.info("Auto select workerThreadNumber={}", Runtime.getRuntime().availableProcessors() * 2);
            return Runtime.getRuntime().availableProcessors() * 2;
        } else {
            return this.workerThreadNumber;
        }
    }


    public int getSchedulerThreadNumber() {
        if (this.schedulerThreadNumber == 0) {
            this.logger.info("Auto select schedulerThreadNumber={}", Runtime.getRuntime().availableProcessors() * 2);
            return Runtime.getRuntime().availableProcessors() * 2;
        } else {
            return this.schedulerThreadNumber;
        }
    }


    public NetworkConfig getNetwork() {
        if (this.network == null) {
            this.network = new NetworkConfig();
        }

        return this.network;
    }


    public ProtocolConfig getTcp() {
        return this.tcp;
    }
    public String toString() {
        String result = String.format("%n\tbossThreadNumber=%s, workerThreadNumber=%s, schedulerThreadNumber=%s%n", this.bossThreadNumber, this.workerThreadNumber, this.schedulerThreadNumber);
        result = result + this.getNetwork().toString();

        if (this.tcp != null) {
            result = result + "\t[tcp]: " + this.tcp.toString();
        }

        return result;
    }

}
