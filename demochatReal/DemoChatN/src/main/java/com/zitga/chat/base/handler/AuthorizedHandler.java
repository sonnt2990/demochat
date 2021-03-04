package com.zitga.chat.base.handler;

import com.zitga.core.handler.socket.AbstractSocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthorizedHandler extends AbstractSocketHandler {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void handle(HandlerContext context, int opCode, ByteBuf in, boolean isTcp) {
		executeHandle(context, opCode, in);
	}
	private void executeHandle(HandlerContext context, int opCode, ByteBuf in) {
		try {
			handle(context,opCode, in);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			logger.error("[AUTHORIZED] FATAL error with, opCode={}",String.valueOf(opCode));
		}
	}
	protected abstract void handle(HandlerContext context, int opCode, ByteBuf in) throws Exception;

}
