package top.okay3r.springboot_example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.okay3r.springboot_example.entity.Employee;
import top.okay3r.springboot_example.service.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/say1")
    public String sayHello(@RequestParam("username") String name) {
        logger.info("say哈哈123 execute..." + name);
        return "Hello," + name;
    }

    @GetMapping("/query/{id}")
    public Employee queryEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = new Employee(id, "mike", 23);
        return employee;
    }

    @Autowired
    private HelloService helloService;

    //测试异步任务
    @GetMapping("/task")
    public String syncTask() {
        this.helloService.doAsyncTask();
        return "job done";
    }

    //测试定时任务
    @GetMapping("/time")
    public String doTimeTask() {
        this.helloService.doAsyncTask();
        return "job done time";
    }
}
