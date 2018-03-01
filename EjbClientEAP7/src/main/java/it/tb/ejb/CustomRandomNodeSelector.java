package it.tb.ejb;


import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jboss.ejb.client.ClusterNodeSelector;

public class CustomRandomNodeSelector implements ClusterNodeSelector {

    private static final Logger logger = LoggerFactory.getLogger(CustomRandomNodeSelector.class);

    public String selectNode(final String clusterName, final String[] connectedNodes, final String[] availableNodes) {

        if (availableNodes.length == 1) {
            logger.info(
                    "\n\n" +
                            "===========================================================\n" +
                            "CustomRandomNodeSelector.availableNodes.length == 1 => " + availableNodes[0] + "\n" +
                            "===========================================================\n"
            );
            return availableNodes[0];
        }
        final Random random = new Random();
        final int randomSelection = random.nextInt(availableNodes.length);

        logger.info(
                "\n\n" +
                        "===========================================================\n" +
                        "CustomRandomNodeSelector.selectNode=" + availableNodes[randomSelection] + "\n" +
                        "===========================================================\n"
        );

        // return a String with one of the node names in connected* or availableNodes
        return availableNodes[randomSelection];
    }
}
