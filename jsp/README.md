## JSP Demo


### Seup

###### With maven

- to install all dependencies: `mvn clean install`
- to start tomcat server: `mvn tomcat7:run`


### Use

- load in browser:
  - for index.html page: `http://localhost:8080/jsp-demo/`
  - for index.jsp page: `http://localhost:8080/jsp-demo/index.jsp`
  - for hello servlet with jsp page: `http://localhost:8080/jsp-demo/hello`

### Debug

- check content of war file:
  - `jar tf target/main.jsp-demo-1.0-SNAPSHOT.war`