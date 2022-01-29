package com.apple.No2.task;


import com.apple.No2.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author Double_apple
 * @Date 2022/1/29 16:32
 * @Version 1.0
 */
public class Task1 {

    public static final String QUEUE="apple";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtil.getChannel();

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

        Scanner scanner= new Scanner(System.in);
        while(scanner.hasNext()){
            String ms = scanner.next();
            channel.basicPublish("",QUEUE,null,ms.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送消息："+ms);
        }

    }
}
