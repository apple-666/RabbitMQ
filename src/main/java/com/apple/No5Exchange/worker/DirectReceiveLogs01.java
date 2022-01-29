package com.apple.No5Exchange.worker;

import com.apple.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class DirectReceiveLogs01 {
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
         Channel channel = ChannelUtil.getChannel();

         //声明一个队列
         channel.queueDeclare("console",false,false,false,null);

         //绑定交换机与队列  后的参数是标签
         channel.queueBind("console",EXCHANGE_NAME,"info");
         channel.queueBind("console",EXCHANGE_NAME,"warning");


         DeliverCallback deliverCallback = (consumerTag, message) -> {
             System.out.println("ReceiveLogsDirect01控制台打印接受到的消息：" + new String(message.getBody()));
         };

         channel.basicConsume("console",true,deliverCallback,consumerTag -> {});
 }
}
