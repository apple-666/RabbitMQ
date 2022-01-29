package com.apple.No2.worker;

import com.apple.No2.util.ChannelUtil;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Double_apple
 * @Date 2022/1/29 16:19
 * @Version 1.0
 */
public class Worker1 {
    public static final String QUEUE="apple";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ChannelUtil.getChannel();

        DeliverCallback deliverCallback = (consumerTag,ms)->{
            System.out.println(new String(ms.getBody()));
        };

        CancelCallback cancelCallback = consumerTag->{
            System.out.println(consumerTag);
        };

        System.out.println("worker 1:工作中");

        channel.basicConsume(QUEUE,true,deliverCallback,cancelCallback);


    }
}
