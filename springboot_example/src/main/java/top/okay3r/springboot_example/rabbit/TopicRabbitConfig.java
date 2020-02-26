package top.okay3r.springboot_example.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue queueA() {
        return new Queue("queueA");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queueB");
    }

    @Bean
    public Queue queueC() {
        return new Queue("queueC");
    }

    @Bean
    public TopicExchange exchange() {

        return new TopicExchange("myExchange");
    }

    @Bean
    public Binding bindingExchangeQueueA(Queue queueA, TopicExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("myTopic.A");
    }

    @Bean
    public Binding bindingExchangeQueueB(Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with("myTopic.B");
    }

    @Bean
    public Binding bindingExchangeQueueC(Queue queueC, TopicExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange).with("myTopic.#");
    }
}
