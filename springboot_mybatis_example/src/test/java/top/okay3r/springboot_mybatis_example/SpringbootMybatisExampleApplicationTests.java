package top.okay3r.springboot_mybatis_example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.okay3r.springboot_mybatis_example.dao.UserMapper;
import top.okay3r.springboot_mybatis_example.entity.User;
import top.okay3r.springboot_mybatis_example.enums.UserSexEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootMybatisExampleApplicationTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQueryUserById() {
        logger.info("hello");
        User user = userMapper.getOne(17L);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("曹操");
        user.setBirthday(new Date());
        user.setSex(UserSexEnum.MAN);
        user.setAddress("Beijing");
        this.userMapper.insert(user);
    }

    @Test
    public void testGetAll() {
        List<User> all = this.userMapper.getAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(18L);
        user.setUsername("关羽");
        this.userMapper.update(user);
    }

    @Test
    public void testDelete() {
        this.userMapper.delete(20L);
    }

}
