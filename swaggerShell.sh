#!/bin/bash

CLASSPATH=/tmp/output/SwaggerAsset.jar 
java -cp $CLASSPATH com.ibmwatson.devopsservices.swaggertestasset.SwaggerCommandLine $1
echo "shell script execution check again"
echo $?
