package com.zitga.chat.server.handler;

import com.zitga.chat.base.constant.OpCode;
import com.zitga.chat.base.handler.AuthorizedHandler;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SocketHandler(OpCode.CLIENT_MESSAGE_RECEIVED)
public class MessasgeSendHandler extends AuthorizedHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void handle(HandlerContext context, int opCode, ByteBuf in) throws Exception {
        ByteBuf out = in.copy();
        out.readerIndex(0);

        context.getPeer().send(out);
    }
}
