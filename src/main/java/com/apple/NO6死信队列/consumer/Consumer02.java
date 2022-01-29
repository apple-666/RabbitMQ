package com.apple.NO6死信队列.consumer;

import com.apple.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/*
 * 死信队列实战
 * 消费者02
 * */
 public class Consumer02 {
 
     //死信队列名称
     public static final String DEAD_QUEUE = "dead_queue";
 
     public static void main(String[] args) throws  Exception{
         Channel channel = ChannelUtil.getChannel();
         System.out.println("等待接收消息......");

         DeliverCallback deliverCallback = (consumerTag, message) -> {
             System.out.println("Consumer02接收的消息是：" + new String(message.getBody()));
         };

         channel.basicConsume(DEAD_QUEUE,true,deliverCallback,consumerTag->{});
     }
}
