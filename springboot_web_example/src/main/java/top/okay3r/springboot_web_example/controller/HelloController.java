package top.okay3r.springboot_web_example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.okay3r.springboot_web_example.entity.Employee;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/say1")
    public String sayHello(@RequestParam("username") String name) {
        logger.info("say1 execute..." + name);
        return "Hello," + name;
    }

    @GetMapping("/query/{id}")
    public Employee queryEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = new Employee(id, "mike", 23);
        return employee;
    }
}
