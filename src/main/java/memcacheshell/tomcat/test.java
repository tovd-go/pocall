package memcacheshell.tomcat;

import org.apache.shiro.codec.Base64;
import utils.ToByteArray;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        byte[] bytes= ToByteArray.toByteArray("D:\\Code\\Java\\pocall\\target\\classes\\com.class");

        System.out.println(Base64.encodeToString(bytes));
    }
}
