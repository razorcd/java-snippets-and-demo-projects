## Servlet Exmaple


### Seup

- create `class` file:
```
javac -d WEB-INF/classes -classpath /home/razor/applications/apache-tomcat-9.0.2/lib/servlet-api.jar HelloServlet.java
```
- move folder to `..../apache-tomcat-9.0.2/webapps` and start Tomcat
- load url: `http://localhost:8080/simple-servlet-demo/hello` in browser


###### With war file:
- to create `war` file run: `jar -cvf simpleservletWar.war .`
- deploy war in Tomcat
- load url `http://localhost:8080/simpleservletWar/hello` in browser

