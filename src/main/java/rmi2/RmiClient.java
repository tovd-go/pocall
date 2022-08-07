package rmi2;

import java.rmi.Naming;
import java.util.Arrays;

public class RmiClient {
    public static void main(String[] args) {
        String host = "127.0.0.1:8888";
        if (args.length > 0)
            host = args[0];
        try {
        	
        	String names[]= Naming.list("rmi://" + host + "/helloService");
        	System.out.println("我是客户端-》远程服务列表："+ Arrays.asList(names));
        	//经测试，这里也会打印HelloServiceImpl中的 System.out.println的内容，所以可以证明会加载远程的HelloServiceImpl类在本地执行
        	//那这样就会出现远程命令执行漏洞
        	HelloService h = (HelloService) Naming.lookup("rmi://" + host + "/helloService");
//            System.out.println("实现“Hello”接口的远程对象: " + h);
//            System.out.println("我在客户端，开始调用RMI服务器端的'sayHello'方法");
//            System.out.println("欢迎,  " + h.sayHello("liuhuiyao"));
        	System.out.println("我是客户端-》这是我调用远程服务的返回"+h.sayHello("张三"));
        } catch (Exception ex) {
            System.out.println("错误 " + ex);
        }
    }
}