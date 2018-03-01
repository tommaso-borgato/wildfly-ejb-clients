package it.tb.ejb;

import it.tb.ejb.stateless.SampleStatelessEjbRemote;
import org.jboss.ejb.client.EJBClientConnection;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.protocol.remote.RemoteTransportProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.naming.client.WildFlyInitialContextFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URI;
import java.util.Properties;

/**
 * JBoss EJB client APIs
 */
public class EJBClientNewApi {

    private static final Logger logger = LoggerFactory.getLogger(EJBClientNewApi.class);

    private Context jndiContext = null;

    public static void main(String[] args) throws Exception {
        EJBClientNewApi instance = new EJBClientNewApi();
        instance.invokeBean("Some name from client...");
    }

    public void invokeBean(String s) throws NamingException {
        final EJBClientContext ejbCtx = new EJBClientContext.Builder()
                .addTransportProvider(new RemoteTransportProvider())
                .setClusterNodeSelector(new CustomRandomNodeSelector())
                .addClientConnection(new EJBClientConnection.Builder()
                .setDestination(URI.create("remote+http://127.0.0.1:8080")).build())
                .build();
            EJBClientContext.getContextManager().setThreadDefault(ejbCtx);

        InitialContext ctx = new InitialContext(getCtxProperties());
        String lookupName = "ejb:EjbServerEar/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote";
        SampleStatelessEjbRemote bean = (SampleStatelessEjbRemote)ctx.lookup(lookupName);
        for (int i = 1; i < 4; i++) {
            int sum = bean.sum(1, 2);
            logger.info(
                    "\n\n" +
                            "===========================================================\n" +
                            "EJBClientNewApi SampleStatelessEjbRemote.sum(1, 2)= " + sum + "\n" +
                            "===========================================================\n"
            );
        }
        ctx.close();
    }

    public static Properties getCtxProperties() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
        return props;
    }
}
