package com.apple.No1.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Double_apple
 * @Date 2022/1/29 15:39
 * @Version 1.0
 */
public class Consumer {

    public static final String QUEUE = "apple";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("110.42.142.78");
        connectionFactory.setUsername("apple");
        connectionFactory.setPassword("1");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明 接收消息
        DeliverCallback deliverCallback = (consumerTag,ms)->{
            System.out.println(new String(ms.getBody()));
        };

        //声明 取消消息
        CancelCallback cancelCallback = consumer ->{
            System.out.println("消息消费中断");
        };


        channel.basicConsume(QUEUE,true,deliverCallback,cancelCallback);
        /*
         * 消费者接收消息
         * 参数1：表示消费哪个UI列
         * 参数2：消费成功之后，是否需要自动应答，true表示自动应答，false表示手动应答
         * 参数3：消费者成功消费的回调
         * 参数4：消费者取消消费的回调
         */
    }
}
