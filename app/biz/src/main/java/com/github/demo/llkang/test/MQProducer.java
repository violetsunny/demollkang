/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.test;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author kll49556
 * @version $Id: Producer, v 0.1 2018/5/25 17:33 kll49556 Exp $
 */
public class MQProducer {
    public static void main(String[] args){
        // 构造Producer
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            // 初始化Producer，整个应用生命周期内，只需要初始化1次
            producer.start();
            // 构造Message
            Message msg = new Message("PushTopic",
                    "push",// tag：给消息打标签,用于区分一类消息，可为null
                    "1",// key：自定义Key，可以用于去重，可为null
                    "Just for test.".getBytes() // body：消息内容
            );
            // 发送消息并返回结果
            SendResult result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PushTopic",
                    "push",
                    "2",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PullTopic",
                    "pull",
                    "1",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 清理资源，关闭网络连接，注销自己
            producer.shutdown();
        }
    }
}
