## Simple Servlet Demo


### Seup

###### With whole folder:

- create `.class` files:
```
javac -d WEB-INF/classes -classpath ....../apache-tomcat-9.0.2/lib/servlet-api.jar *.java
```
- move project files to `..../apache-tomcat-9.0.2/webapps/simple-servlet-demo/` and start Tomcat

###### With war file:

- to create `war` file run: `jar -cvf simpleservletWar.war .`
- deploy the `.war` file in Tomcat

###### With maven

- to install all dependencies: `mvn clean install`
- to start tomcat server: `mvn tomcat7:run`


### Use

- load in browser:
  - for servlet page: `http://localhost:8080/simple-servlet-demo/hello`
  - for filtered page: `http://localhost:8080/simple-servlet-demo/helloThroughFilter`
  - for async servlet page: `http://localhost:8080/simple-servlet-demo/helloasync`
