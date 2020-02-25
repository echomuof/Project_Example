package top.okay3r.springboot_example.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue queuef1() {
        return new Queue("queuef1");
    }

    @Bean
    public Queue queuef2() {
        return new Queue("queuef2");
    }

    @Bean
    public Queue queuef3() {
        return new Queue("queuef3");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fExchange");
    }

    @Bean
    public Binding bindingFExchangeQueuef1(Queue queuef1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queuef1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFExchangeQueuef2(Queue queuef2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queuef2).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFExchangeQueuef3(Queue queuef3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queuef3).to(fanoutExchange);
    }

}
