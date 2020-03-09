import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.okay3r.service.HelloService;

import java.io.IOException;

public class ConsumerRun {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        applicationContext.start();
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        String abcd = helloService.hello("abcd");
        System.out.println(abcd);
    }
}
