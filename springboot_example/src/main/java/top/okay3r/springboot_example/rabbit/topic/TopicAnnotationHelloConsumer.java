package top.okay3r.springboot_example.rabbit.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TopicAnnotationHelloConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "a-q", durable = "true"),
            exchange = @Exchange(value = "a-exchange", durable = "true", type = "topic"),
            key = "aaa.#"
    ))
    public void process(
            @Payload String msg,
            @Headers Map<String, Object> headers,
            Channel channel
    ) throws IOException {
        System.out.println("a~~receive: " + msg);
        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliverTag, false);
    }
}
