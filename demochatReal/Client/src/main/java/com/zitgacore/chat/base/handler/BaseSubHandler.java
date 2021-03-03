package com.zitgacore.chat.base.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zitga.core.handler.socket.support.context.Peer;

import io.netty.buffer.ByteBuf;

public abstract class BaseSubHandler {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract void handle(Peer peer, ByteBuf in) throws Exception;
}
