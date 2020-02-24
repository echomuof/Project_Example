package top.okay3r.ssm.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.okay3r.ssm.asynTask.AsynTaskBean;
import top.okay3r.ssm.entity.User;
import top.okay3r.ssm.mapper.UserMapper;
import top.okay3r.ssm.redis.RedisUtils;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("getj")
    public String getJson() {
        String json1 = (String) this.redisUtils.get("json1");
        logger.info(json1);
        return "ok";
    }

    @GetMapping("setR")
    public String setR() {
        User user = this.userMapper.getOne(19L);
        String json = JSON.toJSONString(user);
        this.redisUtils.set("user19", json);
        return "ok";
    }

    @GetMapping("getR")
    public User getR() {
        String json = (String) this.redisUtils.get("user19");
        User user = JSON.parseObject(json, User.class);
        logger.info(user + "");
        return user;
    }

    @Autowired
    private AsynTaskBean asynTaskBean;

    @GetMapping("do")
    public String doTask() throws InterruptedException {
        this.asynTaskBean.task();
        return "job start";
    }
}
