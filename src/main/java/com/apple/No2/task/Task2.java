package com.apple.No2.task;

import com.apple.No2.util.ChannelUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/*
 * 消息在手动应答时是不丢失、放回队列中重新消费
 * */
 public class Task2 {
 
     // 队列名称
     public static final String TASK_QUEUE_NAME = "ack_queue";
 
     public static void main(String[] args) throws IOException, TimeoutException {
         Channel channel = ChannelUtil.getChannel();
 
         // 声明队列
         channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);
 
         Scanner scanner = new Scanner(System.in);
         while (scanner.hasNext()){
             String message = scanner.next();
             channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
             System.out.println("生产者发出消息："+message);
         }
 
     }
 }
