package top.okay3r.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.okay3r.mp.entity.User;
import top.okay3r.mp.mapper.UserMapper;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootMybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        User user = new User();
        user.setName("⽼王");
        user.setEmail("laowang@kkb.com");
        user.setAge(18);
        userMapper.insert(user);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setAge(18);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id", 2, 5);
        List<User> selectList = this.userMapper.selectList(queryWrapper);
        for (User user1 : selectList) {
            System.out.println(user1);
        }
    }

    @Test
    public void test4() {
        User user = this.userMapper.selectUserByName("春春");
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "老王");
        List<User> users = this.userMapper.selectByMap(map);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
