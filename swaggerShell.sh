#!/bin/bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home
CLASSPATH=/Users/shreejit/Documents/workspace/SwaggerTestCompatibility/output/SwaggerAsset.jar 
$JAVA_HOME/bin/java -cp $CLASSPATH com.ibmwatson.devopsservices.swaggertestasset.SwaggerCommandLine $1
echo $?
