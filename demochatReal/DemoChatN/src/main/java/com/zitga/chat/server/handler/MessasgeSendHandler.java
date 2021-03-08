package com.zitga.chat.server.handler;

import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.base.ConnectionService;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.chat.base.handler.AuthorizedHandler;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.SocketUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SocketHandler(OpCode.CLIENT_MESSAGE_RECEIVED)
public class MessasgeSendHandler extends AuthorizedHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @BeanField
    private ConnectionService connectionService;

    @Override
    protected void handle(HandlerContext context, int opCode, ByteBuf in) throws Exception {
        String message = SerializeHelper.readString(in);
        ByteBuf out = SocketUtils.createByteBuf(opCode);
        SerializeHelper.writeString(out, message);
        for (Peer peer : connectionService.peer) {
            logger.info(peer.toString());

            ByteBuf temp = out.copy();
            peer.send(temp);
        }
        SocketUtils.release(out);
    }
}
