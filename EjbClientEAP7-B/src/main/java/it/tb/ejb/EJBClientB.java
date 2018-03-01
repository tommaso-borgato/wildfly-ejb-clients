package it.tb.ejb;

import it.tb.ejb.stateless.SampleStatelessEjbRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBClientB {

    private static final Logger logger = LoggerFactory.getLogger(EJBClientB.class);

    private Context jndiContext = null;

    public static void main(String[] args) throws Exception {
        EJBClientB instance = new EJBClientB();
        instance.invokeBean("Some name from client...");
    }

    public void invokeBean(String s) throws NamingException {

        if (jndiContext == null) {
            Properties props = new Properties();
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiContext = new InitialContext(props);
        }

        final SampleStatelessEjbRemote businsessBean =
                (SampleStatelessEjbRemote) jndiContext
                        .lookup("ejb:EjbServerEar/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote");

        for (int i = 1; i < 4; i++) {
            int sum = businsessBean.sum(1, 2);
            logger.info(
                    "\n\n" +
                            "===========================================================\n" +
                            "EJBClientB SampleStatelessEjbRemote.sum(1, 2)= " + sum + "\n" +
                            "===========================================================\n"
            );
        }
    }
}
