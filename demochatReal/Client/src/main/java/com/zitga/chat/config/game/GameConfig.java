package com.zitga.chat.config.game;

import com.zitga.bean.annotation.BeanConfiguration;

@BeanConfiguration("config/game.properties")
public class GameConfig {
	private String host;
	private int port;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}
