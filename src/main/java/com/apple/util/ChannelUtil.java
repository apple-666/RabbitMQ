package com.apple.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @Author Double_apple
 * @Date 2022/1/29 16:16
 * @Version 1.0
 */
public class ChannelUtil {

    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("110.42.142.78");
        connectionFactory.setUsername("apple");
        connectionFactory.setPassword("1");

        Connection connection = connectionFactory.newConnection();
        Channel channel =  connection.createChannel();
        return channel;
    }
}
