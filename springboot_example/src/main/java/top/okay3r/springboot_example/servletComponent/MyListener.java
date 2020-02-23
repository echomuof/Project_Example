package top.okay3r.springboot_example.servletComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("应用销毁");
    }
}
