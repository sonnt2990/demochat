package com.zitgacore.chat.server.handler;

import com.zitgacore.chat.base.constant.OpCode;
import com.zitgacore.chat.base.handler.AuthorizedHandler;
import com.zitga.core.annotation.socket.SocketHandler;

@SocketHandler(OpCode.SERVER_MESSAGE_RECEIVED)
public class MessageHandler  extends AuthorizedHandler {
		
}
