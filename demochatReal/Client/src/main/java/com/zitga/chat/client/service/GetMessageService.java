package com.zitga.chat.client.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.chat.base.constant.OpCode;
import com.zitga.core.scheduler.SchedulerService;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.SocketUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@BeanComponent
public class GetMessageService implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private SchedulerService schedulerService;

    @BeanField
    private MasterConnectorService masterConnectorService;

    private ScheduledThreadPoolExecutor scheduler;
    private BufferedReader in;

    @BeanMethod
    public void init() {
        scheduler = schedulerService.getSchedulerThreadGroup();
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void test() {
        scheduler.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        try {
            String string = in.readLine();

            ByteBuf out = SocketUtils.createByteBuf(OpCode.SERVER_MESSAGE_RECEIVED);
            SerializeHelper.writeString(out, string);
            masterConnectorService.send(out);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
