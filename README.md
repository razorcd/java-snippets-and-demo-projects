## Servlet Exmaple


### Seup

- create `class` file:
```
javac -d WEB-INF/classes -classpath /home/razor/applications/apache-tomcat-9.0.2/lib/servlet-api.jar *.java
```
- move folder to `..../apache-tomcat-9.0.2/webapps` and start Tomcat

###### With war file:

- to create `war` file run: `jar -cvf simpleservletWar.war .`
- deploy the .war file in Tomcat


### Use

- load in browser:
  - for servlet page: `http://localhost:8080/simple-servlet-demo/hello`
  - for filtered page: `http://localhost:8080/simple-servlet-demo/helloThroughFilter`
