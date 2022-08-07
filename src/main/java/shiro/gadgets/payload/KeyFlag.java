package shiro.gadgets.payload;

import org.apache.shiro.subject.SimplePrincipalCollection;
import shiro.gadgets.commons.Serializer1;


public class KeyFlag {

    public static void keyflag() throws Exception {
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection();
        Serializer1.serializer("src/main/resources/key1.ser",simplePrincipalCollection);
    }
}
