package com.zitga.chat.client.service;

import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.chat.client.MasterActionListener;
import com.zitga.core.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.core.utils.socket.SocketUtils;
import com.zitga.core.utils.socket.tcpClient.TcpClientConfig;
import com.zitga.core.utils.socket.tcpClient.TcpClientHandler;
import com.zitga.utils.RandomUtils;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.chat.client.constant.MasterOpcode;
import com.zitga.chat.config.game.GameConfig;

import io.netty.buffer.ByteBuf;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@BeanComponent
public class MasterConnectorService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@BeanField
	private MasterActionListener masterActionListener;

	@BeanField
	private ServerConfig serverConfig;

	@BeanField
	private GameConfig gameConfig;

	private TcpClientHandler handler;

	@BeanMethod
	private void init(GameConfig gameConfig) {
		logger.info("Initializing MasterConnectorService ...");

		TcpClientConfig clientConfig = new TcpClientConfig();
		clientConfig.setHeaderSize(serverConfig.getNetwork().getHeaderSize());
		clientConfig.setThreadNumber(serverConfig.getBossThreadNumber());

		handler = new TcpClientHandler(clientConfig);
		handler.registerListener(masterActionListener);
	}

	@BeanDelayedMethod
	public void run() {
		try {
           logger.info("[MASTER] isConnected={}", handler.isConnected());

			if (!handler.isConnected()) {
				connectToMasterServer();
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}


	private void connectToMasterServer() {
		logger.info("[MASTER] Retry connecting to {}:{}", gameConfig.getHost(), gameConfig.getPort());
		handler.connectAsync(gameConfig.getHost(), gameConfig.getPort());
	}

	public void send(ByteBuf out) {
		if (handler.isConnected()) {
			handler.getPeer().send(out);
		} else {
			SocketUtils.release(out);
			logger.error("[MASTER] Still disconnected to {}:{}", gameConfig.getHost(), gameConfig.getPort());
		}
	}
}
