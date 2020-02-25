package top.okay3r.springboot_example.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicHelloProvider {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send1() {
        String msg = "this is msg1";
        System.out.println("Provider Send: " + msg);
        this.amqpTemplate.convertAndSend("myExchange", "myTopic.A", msg);
    }

    public void send2() {
        String msg = "this is msg2";
        System.out.println("Provider Send: " + msg);
        this.amqpTemplate.convertAndSend("myExchange", "myTopic.B", msg);
    }

    public void send3() {
        String msg = "this is msg3";
        System.out.println("Provider Send: " + msg);
        this.amqpTemplate.convertAndSend("myExchange", "myTopic.C", msg);
    }

    public void send4() {
        String msg = "this is msg4";
        System.out.println("Provider Send: " + msg);
        this.amqpTemplate.convertAndSend("myExchange", "myTopic.D", msg);
    }

    public void send5() {
        String msg = "this is msg for annotation";
        System.out.println("Provider Send: " + msg);
        this.amqpTemplate.convertAndSend("a-exchange", "aaa.C", msg);
    }

}
