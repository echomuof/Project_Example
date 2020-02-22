package top.okay3r.springboot_mybatis_example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.okay3r.springboot_mybatis_example.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
