package top.okay3r.springboot_web_example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.okay3r.springboot_web_example.interceptor.LoginInterceptor;

@Configuration
public class MySpringMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            //添加视图处理器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
            }

            //添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
                //需要拦截的请求
                interceptorRegistration.addPathPatterns("/**");
                //不需要拦截的请求
                interceptorRegistration.excludePathPatterns("/", "/index.html", "/login", "/css/**", "/img/**", "/js/**");
            }
        };

        return webMvcConfigurer;
    }
}
