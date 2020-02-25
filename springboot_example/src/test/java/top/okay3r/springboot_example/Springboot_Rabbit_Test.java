package top.okay3r.springboot_example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.okay3r.springboot_example.entity.User;
import top.okay3r.springboot_example.enums.UserSexEnum;
import top.okay3r.springboot_example.rabbit.fanout.FanoutHelloProvider;
import top.okay3r.springboot_example.rabbit.direct.DirectHelloProvider;
import top.okay3r.springboot_example.rabbit.topic.TopicHelloProvider;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Springboot_Rabbit_Test {

    @Autowired
    private DirectHelloProvider directHelloProvider;

    @Test
    public void testDirectSend() throws InterruptedException {
        String msg = "Hello_" + new Date();
        this.directHelloProvider.send("hello-queue", msg);
    }

    @Test
    public void testSimpleDirect2() {
        User user = new User(24L, "mike", new Date(), UserSexEnum.MAN, "Shanghai");
        this.directHelloProvider.send("hello-queue", user);
    }


    @Autowired
    private TopicHelloProvider topicHelloProvider;

    @Test
    public void testTopicSend() throws InterruptedException {
        topicHelloProvider.send1();
        Thread.sleep(500);
        topicHelloProvider.send2();
        Thread.sleep(500);
        topicHelloProvider.send3();
        Thread.sleep(500);
        topicHelloProvider.send4();
    }

    @Test
    public void testAnnotationSend() {
        this.topicHelloProvider.send5();
    }

    @Autowired
    private FanoutHelloProvider fanoutHelloProvider;

    @Test
    public void testFanoutSend() {
        this.fanoutHelloProvider.send();
    }
}

