import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
        applicationContext.start();
        System.out.println("dubbo provider start");
        System.in.read();
    }
}
