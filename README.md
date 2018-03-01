# Traditional client with jboss-ejb-client.properties

# Traditional client without jboss-ejb-client.properties
The client might be configured by using the jboss-ejb-client.properties file or a programatic approach by using the PropertiesBasedEJBClientConfiguration.

# 3 Clustered WilfFly Nodes
./bin/standalone.sh --server-config=standalone-ha.xml "-Dprogram.name=WildFly12 NODE 1" -Djboss.node.name=node1
./bin/standalone.sh --server-config=standalone-ha.xml "-Dprogram.name=WildFly12 NODE 2" -Djboss.node.name=node2 -Djboss.socket.binding.port-offset=100
./bin/standalone.sh --server-config=standalone-ha.xml "-Dprogram.name=WildFly12 NODE 3" -Djboss.node.name=node3 -Djboss.socket.binding.port-offset=200

## Server JNDI bindings
JNDI bindings for session bean named 'SampleStatelessEjb' in deployment unit 'subdeployment "EjbServerJar.jar" of deployment "EjbServerEar.ear"':  
	java:global/EjbServerEar/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote
	java:app/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote
	java:module/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote
	java:jboss/exported/EjbServerEar/EjbServerJar/SampleStatelessEjb!it.tb.ejb.stateless.SampleStatelessEjbRemote
	java:global/EjbServerEar/EjbServerJar/SampleStatelessEjb
	java:app/EjbServerJar/SampleStatelessEjb
	java:module/SampleStatelessEjb
	
## WildFly Java Command
"/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.161-5.b14.fc27.x86_64/bin/java" 
-D"[Standalone]" -server -Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m 
-Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true 
"-Dorg.jboss.boot.log.file=/home/tborgato/projects/wildfly/dist/target/wildfly-node1/standalone/log/server.log" 
"-Dlogging.configuration=file:/home/tborgato/projects/wildfly/dist/target/wildfly-node1/standalone/configuration/logging.properties" 
-jar "/home/tborgato/projects/wildfly/dist/target/wildfly-node1/jboss-modules.jar" 
-mp "/home/tborgato/projects/wildfly/dist/target/wildfly-node1/modules" 
org.jboss.as.standalone -Djboss.home.dir="/home/tborgato/projects/wildfly/dist/target/wildfly-node1" 
-Djboss.server.base.dir="/home/tborgato/projects/wildfly/dist/target/wildfly-node1/standalone"  
'--server-config=standalone-ha.xml'

