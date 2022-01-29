package com.apple.No3持久化.task;

import com.apple.No2.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/*
 * 消息在手动应答时是不丢失、放回队列中重新消费
 * */
 public class Task1 {
 
     // 队列名称
     public static final String TASK_QUEUE_NAME = "apple2";
 
     public static void main(String[] args) throws IOException, TimeoutException {
         Channel channel = ChannelUtil.getChannel();
 
         // 声明队列
         // 第二个参数 true为 持久化队列
         channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
 
         Scanner scanner = new Scanner(System.in);
         while (scanner.hasNext()){
             String message = scanner.next();

             //MessageProperties.PERSISTENT_TEXT_PLAIN  消息持久化(persistent text plain)
             channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
             System.out.println("生产者发出消息："+message);
         }
 
     }
 }
