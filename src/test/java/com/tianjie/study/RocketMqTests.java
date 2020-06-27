package com.tianjie.study;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@SpringBootTest
public class RocketMqTests {

    private  final String groupName = "ad";

    private  final String consumeName = "ada";

    @Test
    public  void SyncProducer() throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        // 设置NameServer的地址
        producer.setNamesrvAddr("192.168.11.250:10181");
        producer.setSendMsgTimeout(10000);
        // 启动Producer实例
        String msg1 = "测试消费流程结束信息";
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("rdp_flow_topic" /* Topic */,
                    "close_instance" /* Tag */,
                    msg1.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    @Test
        public  void AsyncProducer() throws Exception {
            // 实例化消息生产者Producer
            DefaultMQProducer producer = new DefaultMQProducer(groupName);
            // 设置NameServer的地址
            producer.setNamesrvAddr("192.168.11.250:10180");
            // 启动Producer实例
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);

            String msg1 = "{\"src_system\":\"HR\",\"operate_type\":\"UPDATE\",\"org_id\":20013346,\"org_code\":\"213850887\",\"org_name\":\"山东二区.\",\"org_code_sup\":\"213850875\",\"org_id_sup\":20013331,\"display_seq\":4,\"path_alias\":null,\"org_path\":\"顾家家居股份有限公司-国内营销事业部-山东零售公司-山东二区\",\"created\":\"SYSTEM\",\"create_date\":\"2019-03-19 14:59:28\",\"updated\":\"SYSTEM\",\"last_operate_date\":\"2020-04-28 16:59:45\",\"ext_dingding_corp_id\":\"dingbc52122d6249fde335c2f4657eb6378f\",\"ext_dingdingid\":null} ";
            int messageCount = 100;
            // 根据消息数量实例化倒计时计算器
            final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
            for (int i = 0; i < messageCount; i++) {
                final int index = i;
                // 创建消息，并指定Topic，Tag和消息体
                Message msg = new Message("TOPIC_USERCENTER_INTERFACE",
                        "TAG_KUKABPM_ORG",
                        "OrderID188",
                        msg1.getBytes(RemotingHelper.DEFAULT_CHARSET));
                // SendCallback接收异步返回结果的回调
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index,
                                sendResult.getMsgId());
                    }
                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            }
            // 等待5s
            countDownLatch.await(100, TimeUnit.SECONDS);
            // 如果不再发送消息，关闭Producer实例。
            producer.shutdown();
        }


        @Test
        public void consumer() throws InterruptedException, MQClientException {

            // 实例化消费者
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rongzer-consumer");
            final CountDownLatch2 countDownLatch = new CountDownLatch2(1);
            // 设置NameServer的地址
            consumer.setNamesrvAddr("192.168.11.250:10181");
//            consumer.setConsumeThreadMin(1);
//            consumer.setConsumeThreadMax(2);
            consumer.setConsumeTimeout(1000);
            // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
            consumer.subscribe("TOPIC_USERCENTER_INTERFACE", "*");
            consumer.setInstanceName("in1");

            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 注册回调实现类来处理从broker拉取回来的消息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println("1111"+new String(msgs.get(0).getBody()));
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            // 启动消费者实例
            consumer.start();
            countDownLatch.await(100, TimeUnit.SECONDS);
            System.out.printf("Consumer Started.%n");
        }

    @Test
    public void consumer1() throws InterruptedException, MQClientException {

        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("a1");
        final CountDownLatch2 countDownLatch = new CountDownLatch2(1);

        // 设置NameServer的地址
        consumer.setNamesrvAddr("192.168.3.110:9876");
        consumer.setInstanceName("in2");
        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("TopicTest1", "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println("222"+new String(msgs.get(0).getBody()));
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动消费者实例
        consumer.start();
        countDownLatch.await(50, TimeUnit.SECONDS);
        System.out.printf("Consumer Started.%n");
    }
}
