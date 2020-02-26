package top.okay3r.springboot_example.rabbit.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TopicHelloConsumer {

    @RabbitListener(queues = "queueA")
    public void process1(String msg) {
        System.out.println("process queueA received: " + msg);
    }

    @RabbitListener(queues = "queueB")
    public void process2(@Payload String msg,
                         @Headers Map<String, Object> headers,
                         Channel channel) throws IOException {
        System.out.println("process queueB received: " + msg);
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(tag, false);
    }

    @RabbitListener(queues = "queueC")
    public void process3(String msg) {
        System.out.println("process queueC received: " + msg);
    }

}
