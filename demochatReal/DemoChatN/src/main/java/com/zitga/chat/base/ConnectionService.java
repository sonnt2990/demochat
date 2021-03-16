package com.zitga.chat.base;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.chat.server.model.ClientModel;
import com.zitga.chat.server.service.ClientService;
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
    @BeanField
    private ClientService clientService;

    @Override
    public void onConnected(HandlerContext handlerContext) {
        peer.add(handlerContext.getPeer());
        Boolean flag = true;
        List<ClientModel> clients = clientService.allFind();
        if (clients != null) {
            for (ClientModel client : clients) {
                if (client.getId().equals(handlerContext.getPeer().getId())) {
                    flag = false;
                }
            }
            if (flag == true) {
                ClientModel clientModel = new ClientModel(handlerContext.getPeer().getId(), null);
                clientService.clientSave(clientModel);
            }
        }
    }

    @Override
    public void onDisconnected(HandlerContext handlerContext) {
        peer.remove(handlerContext.getPeer());
    }
}
