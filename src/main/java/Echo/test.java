package Echo;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
import java.lang.reflect.Method;

public class test {
    public static void main(String[] args) throws Exception {
        String code ="yv66vgAAAD0AJwoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCgAIAAkHAAoMAAsADAEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwgADgEACGNhbGMuZXhlCgAIABAMABEAEgEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsHABQBABNqYXZhL2lvL0lPRXhjZXB0aW9uCgATABYMABcABgEAD3ByaW50U3RhY2tUcmFjZQcAGQEAJGNvbS90b3ZkL3d3dy9jb250cm9sbGVyL21lbXNoZWxsL2NvbQcAGwEAFGphdmEvaW8vU2VyaWFsaXphYmxlAQAQc2VyaWFsVmVyc2lvblVJRAEAAUoBAA1Db25zdGFudFZhbHVlBYslviEUgvV1AQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACDxjbGluaXQ+AQANU3RhY2tNYXBUYWJsZQEAClNvdXJjZUZpbGUBAAhjb20uamF2YQAhABgAAgABABoAAQAaABwAHQABAB4AAAACAB8AAgABAAUABgABACEAAAAdAAEAAQAAAAUqtwABsQAAAAEAIgAAAAYAAQAAAAYACAAjAAYAAQAhAAAATwACAAEAAAASuAAHEg22AA9XpwAISyq2ABWxAAEAAAAJAAwAEwACACIAAAAWAAUAAAAKAAkADQAMAAsADQAMABEADgAkAAAABwACTAcAEwQAAQAlAAAAAgAm";
        byte[] d = Base64.decodeBase64(code);
//
//
//
//        deserialize(d);

//        Class clazz= Class.forName("java.io.Serializable");
//
//        for (Field field:clazz.getDeclaredFields()){
//            System.out.println(field.getName());
//        }

        Method mdDefineClass = ClassLoader.class
                .getDeclaredMethod("defineClass",String.class,byte[].class,int.class,int.class);
        mdDefineClass.setAccessible(true);
//        System.out.println(mdDefineClass);

        byte[] classBytes = new byte[] {-54,-2,-70,-66,0,0,0,52,0,29,10,0,6,0,15,9,0,16,0,17,8,0,18,10,0,19,0,20,7,0,21,7,0,22,1,0,6,60,105,110,105,116,62,1,0,3,40,41,86,1,0,4,67,111,100,101,1,0,15,76,105,110,101,78,117,109,98,101,114,84,97,98,108,101,1,0,4,109,97,105,110,1,0,22,40,91,76,106,97,118,97,47,108,97,110,103,47,83,116,114,105,110,103,59,41,86,1,0,10,83,111,117,114,99,101,70,105,108,101,1,0,9,77,97,105,110,46,106,97,118,97,12,0,7,0,8,7,0,23,12,0,24,0,25,1,0,11,104,101,108,108,111,32,119,111,114,108,100,7,0,26,12,0,27,0,28,1,0,16,99,111,109,47,115,117,110,116,111,119,110,47,77,97,105,110,1,0,16,106,97,118,97,47,108,97,110,103,47,79,98,106,101,99,116,1,0,16,106,97,118,97,47,108,97,110,103,47,83,121,115,116,101,109,1,0,3,111,117,116,1,0,21,76,106,97,118,97,47,105,111,47,80,114,105,110,116,83,116,114,101,97,109,59,1,0,19,106,97,118,97,47,105,111,47,80,114,105,110,116,83,116,114,101,97,109,1,0,7,112,114,105,110,116,108,110,1,0,21,40,76,106,97,118,97,47,108,97,110,103,47,83,116,114,105,110,103,59,41,86,0,33,0,5,0,6,0,0,0,0,0,2,0,1,0,7,0,8,0,1,0,9,0,0,0,29,0,1,0,1,0,0,0,5,42,-73,0,1,-79,0,0,0,1,0,10,0,0,0,6,0,1,0,0,0,2,0,9,0,11,0,12,0,1,0,9,0,0,0,37,0,2,0,1,0,0,0,9,-78,0,2,18,3,-74,0,4,-79,0,0,0,1,0,10,0,0,0,10,0,2,0,0,0,4,0,8,0,5,0,1,0,13,0,0,0,2,0,14};

        String className = "com.tovd.www.controller.memshell.com";
        Class c1 = (Class)mdDefineClass.invoke(ClassLoader.getSystemClassLoader(),new Object[]{
                className,d,0,d.length
        });
        System.out.println(c1.getName());

//        Class c2 = (Class)mdDefineClass.invoke(new URLClassLoader(new URL[]{}),new Object[]{
//                className,classBytes,0,classBytes.length
//        });
      //  System.out.println("c1.class=" +c1.getClassLoader()+"\nc2.class="+c2.getClassLoader() + "\n"+(c1 == c2));
    }

    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bo);
        oos.writeObject(obj);
        return bo.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);//
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }
}
