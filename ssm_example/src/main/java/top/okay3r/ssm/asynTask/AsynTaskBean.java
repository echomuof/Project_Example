package top.okay3r.ssm.asynTask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsynTaskBean {

    @Async
    public void task() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("do task," + i);
        }
    }
}
