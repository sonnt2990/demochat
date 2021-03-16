package com.zitga.chat.server.handler;

import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.base.ConnectionService;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.chat.base.handler.AuthorizedHandler;
import com.zitga.chat.server.model.MessageModel;
import com.zitga.chat.server.service.MessageService;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.SocketUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SocketHandler(OpCode.SERVER_MESSAGE_RECEIVED)
public class MessageHandler extends AuthorizedHandler {
    @BeanField
    private ConnectionService connectionService;

    @BeanField
    private MessageService messageService;

    @Override
    protected void handle(HandlerContext context, int opCode, ByteBuf in) throws Exception {
        String message = SerializeHelper.readString(in);
        if (message.equals("/Show100")) {
            List<String> messages = messageService.findAllService();
            for (String messagee : messages) {
                ByteBuf out = SocketUtils.createByteBuf(opCode);
                SerializeHelper.writeString(out, messagee);
                context.getPeer().send(out);
            }
        } else {
            MessageModel messageModel = new MessageModel(context.getPeer().getId(), "all", message);
            messageService.messageSaveService(messageModel);
            ByteBuf out = SocketUtils.createByteBuf(opCode);
            SerializeHelper.writeString(out, message);
            for (Peer peer : connectionService.peer) {
                ByteBuf temp = out.copy();
                peer.send(temp);
            }
            SocketUtils.release(out);
        }


    }
}
