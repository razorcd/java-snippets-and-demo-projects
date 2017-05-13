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
  - for login POST `http://localhost:8080/jsp-demo/login`. This will set Params to Session. 
  - for inspecting the current Session attributes: `http://localhost:8080/jsp-demo/session`
  

### Debug

- check content of war file:
  - `jar tf target/main.jsp-demo-1.0-SNAPSHOT.war`