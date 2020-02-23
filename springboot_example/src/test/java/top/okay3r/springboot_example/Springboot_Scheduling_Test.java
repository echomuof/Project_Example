package top.okay3r.springboot_example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.okay3r.springboot_example.service.HelloService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Springboot_Scheduling_Test {
    @Autowired
    private HelloService helloService;

    @Test
    public void testScheduling() {
        this.helloService.dataCount();
    }
}
