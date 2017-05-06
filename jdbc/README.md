## JDBC Demo with core Java and JDBC driver as dependency

### Setup

- create `.class` files in `out` folder using the `src` classpath. Dependencies are not needed.
```
mkdir out
javac -d out -classpath src **/*.java 
```

- execute main Demo class including the jdbc dependencies. 
```
java -cp out:lib/mysql-connector-java.jar project.Demo
```

- create `.jar` file for all `.class` files from the `out` folder.
```
jar cvf Demo.jar -C out/ .
```
- run Jar with dependencies
```
java -cp lib/mysql-connector-java.jar:Demo.jar project.Demo
``` 
- view content of Jar file
```
jar tf Demo.jar 
```