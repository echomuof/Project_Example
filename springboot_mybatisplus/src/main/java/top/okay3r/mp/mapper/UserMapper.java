package top.okay3r.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.okay3r.mp.entity.User;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name=#{name}")
    User selectUserByName(String name);
}
