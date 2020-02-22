package top.okay3r.springboot_mybatis_example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.okay3r.springboot_mybatis_example.dao.UserMapper;
import top.okay3r.springboot_mybatis_example.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public User queryUserById(@PathVariable("id") Long id) {
        return this.userMapper.getOne(id);
    }
}
