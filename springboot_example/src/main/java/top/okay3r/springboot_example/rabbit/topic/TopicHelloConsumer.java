package top.okay3r.springboot_example.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicHelloConsumer {

    @RabbitListener(queues = "queueA")
    public void process1(String msg) {
        System.out.println("process queueA received: " + msg);
    }

    @RabbitListener(queues = "queueB")
    public void process2(String msg) {
        System.out.println("process queueB received: " + msg);
    }

    @RabbitListener(queues = "queueC")
    public void process3(String msg) {
        System.out.println("process queueC received: " + msg);
    }

}
