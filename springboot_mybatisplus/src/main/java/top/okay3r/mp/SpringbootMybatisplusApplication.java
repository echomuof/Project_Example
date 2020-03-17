package top.okay3r.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.okay3r.mp.entity.User;

@SpringBootApplication
@MapperScan("top.okay3r.mp.mapper")
public class SpringbootMybatisplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisplusApplication.class, args);
		User user = new User();
	}

}
