package it.tb.ejb.stateless;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Remote(SampleStatelessEjbRemote.class)
@Stateless
public class SampleStatelessEjb implements SampleStatelessEjbRemote {

    final Logger logger = LoggerFactory.getLogger(SampleStatelessEjb.class);

    public int sum(int a, int b) {
        logger.info("\n" +
                "=========================================\n" +
                "SampleStatelessEjb.sum(" + a + ", " + b + ")=" + (a+b) +
                "=========================================\n"
        );
        return a + b;
    }

    @PostConstruct
    public void pre(){
        logger.info("\n" +
                "=========================================\n" +
                "SampleStatelessEjb.PostConstruct()" +
                "=========================================\n"
        );
    }

    @PreDestroy
    public void post(){
        logger.info("\n" +
                "=========================================\n" +
                "SampleStatelessEjb.PreDestroy()" +
                "=========================================\n"
        );
    }
}
