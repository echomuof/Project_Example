package top.okay3r.springboot_example.rabbit.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutHelloConsumer {

    @RabbitListener(queues = "queuef1")
    public void process1(String msg) {
        System.out.println("Q-1 get " + msg);
    }

    @RabbitListener(queues = "queuef2")
    public void process2(String msg) {
        System.out.println("Q-2 get " + msg);
    }

    @RabbitListener(queues = "queuef3")
    public void process3(String msg) {
        System.out.println("Q-3 get " + msg);
    }
}
