package top.okay3r;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Test1 implements Watcher {
    static Logger logger = LoggerFactory.getLogger(Watcher.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("47.93.10.179:2181", 5000, new Test1());
        logger.info("开始连接");
        logger.info("连接状态{}", zooKeeper.getState());
        Thread.sleep(2000);
        logger.info("连接状态{}", zooKeeper.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("通知：" + watchedEvent);
    }
}
