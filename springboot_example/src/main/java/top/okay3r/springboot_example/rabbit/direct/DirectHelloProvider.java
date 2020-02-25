package top.okay3r.springboot_example.rabbit.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectHelloProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queue, Object msg) {
        System.out.println("Provider Send: " + msg );
        amqpTemplate.convertAndSend(queue, msg);
    }

}
