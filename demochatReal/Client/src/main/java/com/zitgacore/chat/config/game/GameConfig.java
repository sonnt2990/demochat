package com.zitgacore.chat.config.game;

import com.zitga.bean.annotation.BeanConfiguration;

@BeanConfiguration("config/game.properties")
public class GameConfig {
	private String host="localhost";
    private int port=8101;
    private String secret="WzdbC3PQqzGgZYCA";

    
    
//    public String getHost() {
//		return host;
//	}
//
//	public int getPort() {
//		return port;
//	}
//
//	public String getSecret() {
//		return secret;
//	}

	private ConnectorConfig master;

    public ConnectorConfig getMaster() {
        return master;
    }

    @Override
    public String toString() {
        return "GameConfig{ master=" + master +'}';
    }
}
