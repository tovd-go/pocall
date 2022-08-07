package rmi2;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;

public class HelloServiceImpl implements Remote,HelloService, Serializable {
 
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public HelloServiceImpl() {
	        super();
	    }
	    /* 实现本地接口中声明的'sayHello()'方法 */
	    public String sayHello(String message) throws IOException {
	        Runtime.getRuntime().exec("calc.exe");
	        return message;
	    }
}