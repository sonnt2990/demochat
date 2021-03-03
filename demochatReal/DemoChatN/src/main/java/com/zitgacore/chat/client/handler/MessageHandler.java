package com.zitgacore.chat.client.handler;

import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitgacore.chat.base.constant.OpCode;

import io.netty.buffer.ByteBuf;

@SocketHandler(OpCode.CLIENT_MESSAGE_RECEIVED)
public class MessageHandler {		
	public void handle(HandlerContext context, int opCode, ByteBuf in, boolean isTcp) {	
		int snLen = in.readInt();
		int ipLen = in.readInt();

		byte[] bytesServerName = new byte[snLen];
		byte[] bytesIp = new byte[ipLen];

		in.readBytes(bytesServerName);
		in.readBytes(bytesIp);
		
		String serverName  = new String(bytesServerName); 
		String ipAddress   = new String(bytesIp);   

		System.out.println(serverName);
		System.out.println(ipAddress);
	}
}
