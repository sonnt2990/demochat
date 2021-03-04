package com.zitga.chat.client;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.zitga.bean.annotation.BeanMethod;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.chat.client.annotation.MasterHandler;
import com.zitga.chat.config.game.GameConfig;
import com.zitga.core.utils.socket.SerializeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.zitga.chat.base.handler.BaseSubHandler;
import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.utils.socket.tcpClient.ITcpClientListener;

import io.netty.buffer.ByteBuf;

@BeanComponent
public class MasterActionListener implements ITcpClientListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	public int getOpCode() {
		return OpCode.SERVER_MESSAGE_RECEIVED;
	}

	public void onConnected(Peer peer) {
		logger.info("[MASTER] Connected to master server at {}", peer.getRemoteAddress());
	}

	public void onDisconnected() {
		logger.warn("[MASTER] Disconnected to master server");
	}

	public void onReceive(Peer peer, int opCode, ByteBuf in) throws Exception {
        String message = SerializeHelper.readString(in);
        logger.info(message);
	}
}
