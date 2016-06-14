FROM node:argon

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 8080 
CMD [ "npm", "start" ]

#Install java and dependencies
RUN apt-get update && apt-get install -y default-jre

# Copy the execution dependencies on to the container
ADD ./output/SwaggerAsset.jar /tmp/output/SwaggerAsset.jar
ADD ./swaggerShell.sh /tmp/swaggerShell.sh
ADD ./testng.xml /tmp/testng.xml
ADD ./Data.properties /tmp/Data.properties
