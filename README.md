# MBIO-Sinfo
MB.io - SINFO Test Drive Challenge API (backend)

this application was built using java8, Spring (to handle the requests), maven (to handle dependencies) and Jackson to parse the Json files.

In order to run the application:

1- Clone the repository or extract the zip.
2- Move to the MBIO-Sinfo directory.
3- Run the command: mvn clean package
4- Run the application: java -jar target/testdrive-rest-0.1.0.jar

Running the tests:
1- Run mvn clean test.

I chose Spring because it encapsulates many of the responsabilities of handelling requests, it makes te code much more readeble and simple.
I created a dealerId in the vehicle because I felt the need to know which dealer the vehice belonged to without having to run trough the list of dealers every time. Created some DTOs in order to better procted data and keep the request and responses as simple as possible, there are a few missing and in the future a interface shared by all of them should be created.

All of the base requests were fulfilled. However, some tests were only run using postman to simulate the requests because I had trouble creating the objects for unit testing and didn't had much time to dedicate to the project due to my thesis workload.

