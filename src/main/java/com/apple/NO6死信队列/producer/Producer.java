package com.apple.NO6死信队列.producer;

import com.apple.util.ChannelUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/*
 * 死信队列之生产者代码
 *
 * */
 public class Producer {
 
     //普通交换机的名称
     public static final String NORMAL_EXCHANGE = "normal_exchange";
     public static void main(String[] args) throws Exception{
         Channel channel = ChannelUtil.getChannel();
 
         //死信消息，设置TTL时间  单位是ms  10000ms是10s
         AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
 
         for (int i = 0; i < 10; i++) {
             String message = "info" + i;
             channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes(StandardCharsets.UTF_8));
         }
     }
 }
