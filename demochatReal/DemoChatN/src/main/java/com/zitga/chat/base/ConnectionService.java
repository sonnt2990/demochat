package com.zitga.chat.base;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.core.handler.socket.support.ISocketConnectionListener;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.handler.socket.support.context.Peer;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@BeanComponent
public class ConnectionService implements ISocketConnectionListener {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    public List<Peer> peer = new ArrayList<>();

    @Override
    public void onConnected(HandlerContext handlerContext) {
        try {
            peer.add(handlerContext.getPeer());
        } catch (Exception e) {
            logger.info("Loi when connect ", e);
        }
    }

    @Override
    public void onDisconnected(HandlerContext handlerContext) {
        try {
            peer.remove(handlerContext.getPeer());
        } catch (Exception e) {
            logger.info("Loi when disconnect ", e);
        }
    }
}
