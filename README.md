# Wolfpack
Internship assignment for Wolfpack.

## QuickStart
Important note: To build this app using the IntelliJ IDE, Java SDK 11 is required.

#### Starting the API
1. Load the project into IntelliJ.
2. In the project-overview navigate to src/main/java/nl/ramonpeek/Application.java.
3. Right-click on Application.java and select Run 'Application.main()'.

Once the API is booted successfully, it will print a link to the Swagger UI in the output. If the output is not visible, it is also possible to access Swagger via: [Localhost](http://localhost:8080/swagger-ui.html#/).

#### Starting the tests
1. Load the project into IntelliJ.
2. In the project-overview navigate to src/test/nl/ramonpeek/tests.
3. Right-click on the test-package and select Run 'Tests in 'nl.ramonpeek.tests''.

IntelliJ should show a box which contains all tests, and whether they failed or passed.

## Notes regarding implementation

#### Comments
If a class implements an interface, I put the method describing comments of that class in the interface. When more clarification is needed in the code, I add comments in-line.

#### Checks in both controller- and logic layers
Logic-checks are executed in both the controller- and manager-layer in order to provide as detailed responses as possible in the HTTP response.

#### Seperate endpoint for location updates
I first thought it would be handy to only use the updateWofl endpoint for updating a wolf's location, but I have changed it as it is an endpoint which probably will be requested a lot, so it is better to keep it seperated.

#### Only one context implemented
For the sake of this assignment, I have added only a memory (mock) context. I have added an empty Mongo Context to display the flow of how I would be able to change context and get data from a different data source.
