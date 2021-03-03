package com.zitgacore.chat.config.game;

import com.zitga.bean.annotation.BeanConfiguration;

@BeanConfiguration("config/game.properties")
public class GameConfig {
	
    private ConnectorConfig master;

    public ConnectorConfig getMaster() {
        return master;
    }

    @Override
    public String toString() {
        return "GameConfig{ master=" + master +'}';
    }
}
