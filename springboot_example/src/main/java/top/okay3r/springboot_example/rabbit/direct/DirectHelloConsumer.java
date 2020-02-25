package top.okay3r.springboot_example.rabbit.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.okay3r.springboot_example.entity.User;

@Component
public class DirectHelloConsumer {


    /*@RabbitListener(queues = "hello-queue")
    public void process1(String msg) {
        System.out.println("Customer1 Received:  " + msg);
    }

    @RabbitListener(queues = "hello-queue")
    public void process2(User msg) {
        System.out.println("Customer2 Received:  " + msg);
    }*/

    public void process3(User msg) {
        System.out.println("Customer2 Received:  " + msg);
    }
}


