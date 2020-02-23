package top.okay3r.springboot_example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;


}

