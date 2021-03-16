package com.zitga.chat.client.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.tcpClient.ITcpClientListener;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
