package com.fiction.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息消费者，接受消息并处理
 */
@Slf4j
@Component
@RabbitListener(queues = "confirm_test_queue")
public class ReceiverMessageSecond {

    private int retryNum = 5;

    private int currentNum = 0;

    @RabbitHandler
    public void processHandler(CorrelationData correlationData , String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("消费者 2 号收到：{}", msg);
            String correlationId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
            System.out.println(correlationId);
            log.info("correlationId:{}",correlationId);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}