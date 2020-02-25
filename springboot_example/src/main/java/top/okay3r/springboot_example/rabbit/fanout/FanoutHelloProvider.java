package top.okay3r.springboot_example.rabbit.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutHelloProvider {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        System.out.println("send fanout success ......");
        this.amqpTemplate.convertAndSend("fExchange", "", "hahaha");
    }
}
