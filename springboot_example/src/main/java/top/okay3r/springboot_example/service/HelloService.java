package top.okay3r.springboot_example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    //异步方法
    @Async
    public void doAsyncTask() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据保存完毕");
    }

    private static int count = 0;

    //定时任务
    @Scheduled(cron = "0/3 * * * * ? ")
    public void dataCount() {
        System.out.println("记录一下：" + count++);
    }
}
