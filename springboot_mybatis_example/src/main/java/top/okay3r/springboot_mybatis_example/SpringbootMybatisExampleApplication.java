package top.okay3r.springboot_mybatis_example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.okay3r.springboot_mybatis_example.dao")
public class SpringbootMybatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisExampleApplication.class, args);
    }

}
