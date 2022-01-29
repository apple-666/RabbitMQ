package com.apple.No1.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Author Double_apple
 * @Date 2022/1/29 15:06
 * @Version 1.0
 */
public class Producer {


    public static final String QUEUE = "apple";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("110.42.142.78");
        factory.setUsername("apple");
        factory.setPassword("1");
        //这个端口号 指 请求发送ms的端口
//        factory.setPort(1567);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
        /*  queueDeclare()
         * 生成一个队列
         * 参数1：队列名称
         * 参数2：队列里面的消息是否持久化，默认情况下，消息存储在内存中
         * 参数3：该队列是否进行消费共享
         *        true可以多个消费者消费
         *        false只能一个消费者消费
         * 参数4：是否自动删除：最后一个消费者断开连接之后，该队列是否自动删除，true则自动删除，
         *        false不自动删除
         * 参数5：其他参数
         * */

        String ms = "hello remote for apple";
        channel.basicPublish("",QUEUE,null, ms.getBytes(StandardCharsets.UTF_8));
        /* basicPublish()
         * 发送一个消息
         * 参数1：发送到哪个交换机
         * 参数2：路由的key值是那个，本次是队列的名称
         * 参数3：其他参数信息
         * 参数4：发送消息的消息体
         * */
        System.out.println("消息发送完毕！");

    }
}
