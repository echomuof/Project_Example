package top.okay3r.springboot_example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.okay3r.springboot_example.servletComponent.MyFilter;
import top.okay3r.springboot_example.servletComponent.MyListener;

import java.util.Arrays;

/***
 * servlet过滤器、监听器配置类
 */

@Configuration
public class MyServletConfig {
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new MyFilter());
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<MyListener> bean = new ServletListenerRegistrationBean<>(new MyListener());
        return bean;
    }
}
