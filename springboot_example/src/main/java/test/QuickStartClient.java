package test;

import com.meituan.dorado.config.service.ReferenceConfig;
import org.apache.thrift.TException;

public class QuickStartClient {
    public static void main(String[] args) {
        ReferenceConfig<HelloService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setAppkey("com.meituan.octo.dorado.client");           //调用端appkey
        referenceConfig.setRemoteAppkey("com.meituan.octo.dorado.server");     //服务端appkey
        referenceConfig.setServiceInterface(HelloService.class);               //服务接口
        referenceConfig.setRegistry("mock");                                   //服务发现, mock伪注册中心
        referenceConfig.setDirectConnAddress("localhost:9001");                //直连访问
        
        HelloService client = referenceConfig.get();
        try {
            String result = client.sayHello("OCTO");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}