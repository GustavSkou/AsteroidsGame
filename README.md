Build the game modules from the root folder:
mvn clean install

Run the JavaFX game from the root folder:
mvn exec:exec

Run the score microservice as a separate process (HTTP):
mvn -f score/pom.xml spring-boot:run

Score endpoints:
http://localhost:8081/hello
http://localhost:8081/score?point=10
