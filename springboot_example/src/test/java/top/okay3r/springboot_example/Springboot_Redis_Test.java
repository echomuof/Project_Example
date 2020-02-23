package top.okay3r.springboot_example;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import top.okay3r.springboot_example.dao.UserMapper;
import top.okay3r.springboot_example.entity.User;
import top.okay3r.springboot_example.redis.RedisUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Springboot_Redis_Test {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testSet() {
        Long id = 17L;
        User user = this.userMapper.getOne(id);

        boolean set = redisUtils.set("user_" + id, user);
        logger.info(set + "");
    }

    @Test
    public void testGet() {
        String key = "user_17";
        User user = (User) this.redisUtils.get(key);
        logger.info(user + "");
        String s = JSON.toJSONString(user);
        logger.info(s);
        this.redisUtils.set("json1", s);

    }

    @Test
    public void testJson() {
        String json = (String) this.redisUtils.get("json1");
        User user = JSON.parseObject(json, User.class);
        logger.info(user + "");
    }

}
