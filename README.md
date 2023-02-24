# covid-data-with-spring

#Requirements: 
  1. Java 17
  2. Docker
  3. Maven
  4. Postman
  
#To start the application, 
   1. Run in a terminal (inside the project directory) docker-compose up or if you are using Intellij, press run inside the file.
   2. Run the application.
   3. Use Postman to access the endpoints using the urls.
    
#You can access the required endpoints in the following urls:
   1. http://localhost:9090/regionalUnit/{id} (where {id}, specify the area id you want)
   2. http://localhost:9090/regionalUnit/dailyVaccinations/{id}
   3. http://localhost:9090/regionalUnit/percentage
   
   
 #Possible additions/refactoring:
  1. Enrich the exception handling.
  2. Create a full test suite.
  3. Rework the data parsing and possibly use liquibase.
  4. Refactor the Services and collect the common code under a util.
  5. Implement a UI.
 
