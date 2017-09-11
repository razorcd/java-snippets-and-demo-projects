1. mysql DB with database: `spring_app_db`

User and password can be foung in `application.yml`


2. for first time load set `datasource.initialise: true`   so it runs the `schema.sql` script


3. (will be created automatically) X start mongo with database: spring_db_mongo
 
 to open mongo CLI run: `mongo spring_db_mongo` 
 
 
 
 ## JavaDoc test:
 - run `mvn javadoc:javadoc`
 - open `file:///...../target/site/apidocs/index.html`