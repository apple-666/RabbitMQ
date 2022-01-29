package com.apple.No3持久化.worker;

import com.apple.No2.util.ChannelUtil;
import com.apple.No2.util.SleepUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
 * 消费者
 * */
 public class Worker2 {
     // 队列名称
     public static final String TASK_QUEUE_NAME = "apple2";
 
     public static void main(String[] args) throws IOException, TimeoutException {
         Channel channel = ChannelUtil.getChannel();
         System.out.println("C2等待接受消息处理时间较长");

         DeliverCallback deliverCallback = (consumerTag, message) -> {
             int sec=4;
             SleepUtil.sleep(sec);
             System.out.println("沉睡："+sec);
             System.out.println("接受到的消息是:"+new String(message.getBody()));

             //进行手动应答
             /*
             * 参数1：消息的标记  tag
             * 参数2：是否批量应答，false：不批量应答 true：批量
             * */
             channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
     };

     int prefetchCount = 5;
     channel.basicQos(prefetchCount);

     // 采用手动应答
     boolean autoAck = false;
     channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag) -> {
         System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
     });
 }
}
