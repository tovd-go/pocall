package rmi2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
    public static void main(String[] args) {
        // 在服务器端设置安全机制         
        /*           if (System.getSecurityManager() == null) {               System.setSecurityManager(new RMISecurityManager());            }        */         
        try {
            System.out.println("开始 RMI Server ...");
            LocateRegistry.createRegistry(8888);
            /* 创建远程对象的实现实例 */
            HelloServiceImpl hImpl = new HelloServiceImpl();
            System.out.println("将实例注册到专有的URL ");
            Naming.rebind("rmi://127.0.0.1:8888/helloService",  hImpl);
            // 把远程对象注册到RMI注册服务器上，并命名为RHello
            // 绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的）
//            Naming.bind("rmi://localhost:8888/RHello", rhello);
            // Naming.bind("//localhost:8888/RHello",rhello);
            System.out.println("等待RMI客户端调用...");
            Thread.sleep(10000000);
            System.out.println("结束");
        } catch (Exception e) {
            System.out.println("错误: " + e);
        }
    }
}