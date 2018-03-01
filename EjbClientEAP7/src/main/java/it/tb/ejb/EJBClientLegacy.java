package it.tb.ejb;

import it.tb.ejb.stateless.SampleStatelessEjbRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * JNDI approach
 */
public class EJBClientLegacy {

    private static final Logger logger = LoggerFactory.getLogger(EJBClientLegacy.class);

    private Context jndiContext=null;

    public static void main(String[] args) throws Exception {
        EJBClientLegacy instance = new EJBClientLegacy();
        instance.invokeBean("Some name from client...");
    }

    public void invokeBean(String s) throws NamingException {

        if (jndiContext==null) {
            final Properties jndiProperties = new Properties();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiContext = new InitialContext(jndiProperties);
        }

        final SampleStatelessEjbRemote businsessBean =
                (SampleStatelessEjbRemote) jndiContext
                .lookup("ejb:EjbServerEar/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote");

        for (int i = 1; i < 4; i++) {
            int sum = businsessBean.sum(1, 2);
            logger.info(
                    "\n\n" +
                            "===========================================================\n" +
                            "EJBClientLegacy SampleStatelessEjbRemote.sum(1, 2)= " + sum + "\n" +
                            "===========================================================\n"
            );
        }
    }
}
