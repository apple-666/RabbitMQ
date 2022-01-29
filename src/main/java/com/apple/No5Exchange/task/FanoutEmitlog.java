package com.apple.No5Exchange.task;

import com.apple.util.ChannelUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
 *  发消息 交换机
 * */
 public class FanoutEmitlog {

     // 交换机的名称
     public  static  final String EXCHANGE_NAME = "apple";
 
     public static void main(String[] args) throws  Exception{
         Channel channel = ChannelUtil.getChannel();
         channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

         Scanner scanner = new Scanner(System.in);
         while (scanner.hasNext()){
             String message = scanner.next();
             channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes(StandardCharsets.UTF_8));
             System.out.println("生产者发出的消息："+ message);
         }
     }
}
