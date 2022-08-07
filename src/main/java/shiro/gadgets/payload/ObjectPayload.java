package shiro.gadgets.payload;

import com.sun.xml.internal.ws.util.StringUtils;



public interface ObjectPayload<T> { T getObject(Object paramObject) throws Exception;

    public static class Utils {
        public static Class<? extends ObjectPayload> getPayloadClass(String className) {
            Class<? extends ObjectPayload> clazz = null;
            try {
                clazz = (Class)Class.forName("shiro.gadgets.payload." + StringUtils.capitalize(className));
            } catch (Exception exception) {}

            return clazz;
        }
    }
}