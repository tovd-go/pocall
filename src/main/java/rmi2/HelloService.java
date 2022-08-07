package rmi2;

import java.io.IOException;

public interface HelloService {
	public String sayHello(String message) throws IOException;
}