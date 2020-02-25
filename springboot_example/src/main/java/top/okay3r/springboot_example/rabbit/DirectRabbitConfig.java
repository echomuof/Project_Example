package top.okay3r.springboot_example.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Rabbit配置类（直连方式）
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("hello-queue");
    }
}
