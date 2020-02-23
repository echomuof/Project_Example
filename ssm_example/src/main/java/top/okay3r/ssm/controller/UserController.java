package top.okay3r.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.okay3r.ssm.entity.User;
import top.okay3r.ssm.mapper.UserMapper;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("getUser")
    public String getUser() {
        this.userMapper.delete(19L);
        // return user;
        return "ok";
    }
}
