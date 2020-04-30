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

## Design
This section will contain all the technical designs for the WolfPack app. As I will be focusing only on the backend of a single project, I decided to only create a component diagram and class diagram instead of a complete C4-model. At the end of the document I will go in depth about the packages I will be using and their responsibilities.

#### Frameworks and libraries
![Frameworks](https://i.imgur.com/hYM52YQ.png)

For the backend framework I will be using Spring framework. I have chosen for this framework as I am most comfortable with programming in Java and I like the large amount of library support. Spring also supports a wide range of libraries such as Spring security, web and session which are easily implementable in the application if needed.

The assignment only mentions the design and implementation of the backend, but I like to add a simple frontend for using the REST endpoints. For this frontend I will be using the Swagger framework as it gives a clear overview of all the endpoints and it provides the default layout of the request-body for each request.

For testing I have decided to use JUnit 5 as it is easy to quickly setup unit tests and it is automatically integrated in IntelliJ to view the test results. I also like to be able to decide which tests I want to run, and JUnit 5 has this functionality.

For model-validation I decided to use Hibernate Validation. It allows for model validation based on annotations like @Null or @Required. Especially as an API can be very dependent on the incoming request-body, I decided to add this as an extra validation check.

#### Class diagram
The following class diagram is set up using the provided requirements in the assignment. I am assuming that a pack can consist of 1 or more wolves, and that a wolf can also be present in multiple packs.
![Class diagram](https://i.imgur.com/clPHETD.png)

#### Component diagram
The WolfPack API backend will make use of the Spring framework. The controllers will receive the http-request and will return data via JSON if possible. Invalid requests will be handled in the controller as well. The wolf- and packcomponent in the diagram below represent the code which is necessary to transfer data. I will go more in depth about the implementation of the components in the next section.

![Component diagram](https://i.imgur.com/jVE46eT.png)

#### Layer responsibilities and dataflow
I have decided to make use of a multi-layered software architecture (derived from the repository-pattern) which consists of 4 mandatory layers, and 2 supporting layers. The mandatory layers determine the flow of the data (Controller -> Manager -> Repo -> Dal) and the 2 supporting layers help accomplish the flow.
| Layer | Function |
|-------|----------|
| Configuration | Provide configuration-settings for the application. |
| Controllers | Intercept incoming http-requests. |
| Managers | Execute all logic-based checks. |
| Repositories | Specify which data source to use. |
| DAL | Getting data from the data source. |
| Models | Provide classes which are known to all other layers for transferring, filling and retrieving data. |

![Layer diagram](https://i.imgur.com/gVdUeHV.png)

#### Package diagram
Besides the default C4-Model diagrams, I have decided to add a package diagram which shows the relations and structure between the different packages/layers I use. Each layer is separated by an interface so the implementation can be swapped easily. Each layer also has access to the models-package for manageable data transfer. To keep a clear overview, I have not specified the implementation of classes in this diagram. In the next section I will add full specifications (including implementations) of each individual layer and class.

![Package diagram](https://i.imgur.com/dQHVekb.png)

#### Class specifications
- Configuration

![Configuration](https://i.imgur.com/JfaE9eG.png)

- Controllers

![Controllers](https://i.imgur.com/sFIKHZT.png)

- Managers

![Managers](https://i.imgur.com/fblrDfK.png)

- Repositories

![Repositories](https://i.imgur.com/rPr6NEY.png)

- DAL

![DAL](https://i.imgur.com/ag8kl7M.png)

