#!/bin/bash

CLASSPATH=/tmp/output/SwaggerAsset.jar 
java -cp $CLASSPATH com.ibmwatson.devopsservices.swaggertestasset.SwaggerCommandLine $1
echo "check deploy container piece working taking too long"
echo $?
