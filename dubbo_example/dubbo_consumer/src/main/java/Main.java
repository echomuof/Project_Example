import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.okay3r.service.HelloService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        applicationContext.start();
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        System.out.println(helloService.hello("mikeaaa"));
        // System.in.read();
    }
}
