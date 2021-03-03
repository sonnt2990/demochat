package com.zitgacore.chat.base.handler;

import com.zitga.core.handler.socket.AbstractSocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;

import io.netty.buffer.ByteBuf;

public class AuthorizedHandler extends AbstractSocketHandler {

	@Override
	public void handle(HandlerContext context, int opCode, ByteBuf in, boolean isTcp) {	
        for (int i=0;i<in.readInt();i++) {
        	System.out.print(in.readerIndex(i));       	
        }
	}

}
