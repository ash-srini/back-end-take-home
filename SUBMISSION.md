RouteFinder application is built using Java, Spring Boot and Maven.

## How to Run:
To build the project, you would need maven installed.
Navigate to the RouteFinder folder and use 'mvn clean install' command to build the project.
 
You can run the jar using  java -jar ./target/shortest-route-finder-0.0.1-SNAPSHOT.jar

## GET Request
You can either use a browser/postman to make a get request in the following manner:
http://localhost:8080/route?origin=YYZ&destination=JFK

OR

Use curl to make a get request:
curl -v 'http://localhost:8080/route?origin=YYZ&destination=JFK'


## Logic behind the shortest path algorithm

I am using BFS for finding the shortest path.
Since the shortest route is defined as the one with the least number of connections each connection has the same weight. 
Hence we can use BFS for finding the shortest path.



