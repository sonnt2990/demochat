package com.zitgacore.chat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.core.utils.socket.SocketUtils;
import com.zitga.core.utils.socket.tcpClient.TcpClientConfig;
import com.zitga.core.utils.socket.tcpClient.TcpClientHandler;
import com.zitga.utils.RandomUtils;
import com.zitgacore.chat.base.constant.OpCode;
import com.zitgacore.chat.client.constant.MasterOpcode;
import com.zitgacore.chat.config.game.ConnectorConfig;
import com.zitgacore.chat.config.game.GameConfig;
import com.zitgacore.chat.config.server.ServerConfig;

import io.netty.buffer.ByteBuf;

@BeanComponent
public class MasterConnectorService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @BeanField
    private MasterActionListener masterActionListener;

    @BeanField
    private ServerConfig serverConfig;

    private ConnectorConfig connectorConfig;
    
    private TcpClientHandler handler;

    @BeanMethod
    private void init(GameConfig gameConfig) {
        logger.info("Initializing MasterConnectorService ...");

        connectorConfig = gameConfig.getMaster();

        TcpClientConfig clientConfig = new TcpClientConfig();
        clientConfig.setHeaderSize(serverConfig.getNetwork().getHeaderSize());
        clientConfig.setThreadNumber(serverConfig.getBossThreadNumber());

        handler = new TcpClientHandler(clientConfig);
        handler.registerListener(masterActionListener);
    }

    public void run() {
        try {
//           logger.trace("[MASTER] isConnected={}", handler.isConnected());

            if (!handler.isConnected()) {
                connectToMasterServer();
            } else {
                ping();
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void connectToMasterServer() {
        logger.info("[MASTER] Retry connecting to {}:{}", connectorConfig.getHost(), connectorConfig.getPort());
        handler.connectAsync(connectorConfig.getHost(), connectorConfig.getPort());
    }

    private void ping() {
        ByteBuf out = SocketUtils.createByteBuf(OpCode.MASTER_SERVER_ACTION);
        out.writeByte(MasterOpcode.PING);
        out.writeByte(RandomUtils.nextInt(100));

        send(out);
//       logger.info("Ping to {}", handler.getPeer().getRemoteAddress());
    }
    public void send(ByteBuf out) {
        if (handler.isConnected()) {
            handler.getPeer().send(out);
        } else {
            SocketUtils.release(out);
            logger.error("[MASTER] Still disconnected to {}:{}", connectorConfig.getHost(), connectorConfig.getPort());
        }
    }
}
