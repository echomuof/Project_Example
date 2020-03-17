package test;

import com.meituan.dorado.config.service.ProviderConfig;
import com.meituan.dorado.config.service.ServiceConfig;

public class QuickStartServer {
    public static void main(String[] args) {
        ServiceConfig<HelloService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setServiceInterface(HelloService.class);        // 服务接口
        serviceConfig.setServiceImpl(new HelloServiceImpl());         // 服务实现类
        
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setAppkey("com.meituan.octo.dorado.server123");           // 服务appkey
        providerConfig.setServiceConfig(serviceConfig);                       // 服务接口类
        providerConfig.setRegistry("mock");                                   // 服务注册, mock伪注册中心
        providerConfig.setPort(9001);                                         // 服务端口号
        providerConfig.init();                                                // 启动服务
    }
}