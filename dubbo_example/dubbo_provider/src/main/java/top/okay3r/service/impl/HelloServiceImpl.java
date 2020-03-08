package top.okay3r.service.impl;

import top.okay3r.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println(name + " execute provider");
        return "Hello " + name;
    }
}
