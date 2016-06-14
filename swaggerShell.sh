#!/bin/bash

CLASSPATH=/tmp/output/SwaggerAsset.jar 
java -cp $CLASSPATH com.ibmwatson.devopsservices.swaggertestasset.SwaggerCommandLine $1
echo "anything wrong with ibm containers 1045 change"
echo $?
