# Kolomolo Java Test - Part 1

## General aptitude test

### Can you tell me what annoys you in Java SpringBoot the most? What features would you like to have in Java SpringBoot that you don't have now?
```
Working with Spring Boot, it provides a lot of configuration options, that sometimes can annoying and lead to complexity at the start or when you are trying to setup a new project and combine many modules.
I wish to have better documentation of Spring Boot, I mean there are tons of documentation and sometimes it requires time to understand communication/configuration between different Spring Boot modules.
Also, Improvements in dependency injection management would be great becasue sometimes it lead to the issues such as circular dependencies and etc.

I would like to have more built-in support for common design patterns, it could simplify the implementation of these patterns in Spring Boot applications.
```

### How would you go about creating a Java service on the cloud? Specify steps and explain why.
```
First of all I would choose cloud provider like AWS or GCP, depends of pricing and client requirements.
Second step is creating of project architecture, understanding what we need like monolith or microservices, requirements, technologies, create UML diagrams, etc. It's important initial step to understand whole picture of the project.
Then create an application, services, controllers via Spring Boot and other technologies.
Create CI-CD flow to build Docker image and push to the cloud like AWS ECR.
Deploy it to the AWS ECS with all configuration files like how your service should be deployed, including the number of replicas, network configuration, etc. 
Configure auto-scaling policies to automatically scale our app based on metrics: CPU utilization, memory usage, etc.
Add monitoring like AWS CloudWatch with alerts and etc. It helps to check the state of application.|
Connected AWS WAS and AWS Inspector to check and fix vulnerabilities and track requests.
Ofcourse it depends of the price that the client can spend but all these features increase security.
```

### Do you know any other ways of configuring Java SprintBoot apps but the application context file? Why would you use them? What are the benefits?
```
There are a few ways to configure Spring Boot;
XML config (didn't use it long time)
Spring Annotation config like @Configuration, @Bean, etc 
Property files: application.properties/yml
Profiles that allows to have different configuration for as an example: local, dev, prod

I like to use all parts except XML-based configuration. 
It's simplify the process of configuration and gives more flexibility.
```

### Did you work with any enterprise solutions and if yes - tell me more about this experience?
```
The main goal was related to the integration service with external platforms and secure the middleware to avoid vulnerabilities, I used AWS that helps me to cover it.
```
### Explain your process for debugging errors in your Java code.
   -  What debugging techniques do you find most effective?
   - How do you approach identifying the root cause of an error?
   - Do you have any preferred tools or strategies for debugging complex issues?
   ```
   Debugging process is a complex of actions. In my experience, usaully, I used IDE debugging, logger, Jprofiler, etc.
   First step is to reproduce the error. Sometimes its hard to do it on a local machine, there is posibbility that the error depends on data or environment.
   Then I analize the root cause using all information and tools.
   ```
### How do you write clean and maintainable Java code?
   - What coding conventions or best practices do you follow?
   - How do you document your code for better understanding by others?
   - Describe your strategies for ensuring code readability and maintainability over time.
   ```
   I'm trying to follow a few principals:
   SOLID it's give ability to write effective code, structured and reusable.
   DRY, KISS principals help to make the code clean + such tool like SonarCude can be used to monitor quality and test coverage. Even IntelliJ Idea has a tool to check how many % of code is covered by tests.
   Sometimes I can write comments in the code (for me and for other people).
   Unit tests, refactoring
   ```
### Explain your approach to testing your Java code.
   - What types of tests do you typically write (e.g., unit tests, integration tests)?
   - How do you ensure your tests cover various functionalities and edge cases?
   - Do you utilize any testing frameworks or tools?
   ```
   "Writing tests" is important part of development and it's necessery to do to make your job better. It's avoid a lot of bugs and errors at the start.
   I'm writing Unit and Integration tests via Spring JUnit and Mockito
   We can use SonarCube as I said before to make sure that it covers some % of code.
   ```
### Describe a situation where you got stuck on a Java problem. How did you approach it?
   - What troubleshooting steps did you take?
   - Did you seek help from others, or what resources did you utilize?
   - How did this experience influence your problem-solving approach?
   ```
   From my experiance, I saw a lot of different tricky issues, here is a few of them:
   Memory leak when the service is overloaded, to investigate this issue I used JProfiler, the problem was with the not utilizing objects.

   Second one is too long response from the server when processing request. the problem was with optimization of database, solution was the optimization indexes and adding caching.
   
   Race condition - that resulted in unexpected behavior and intermittent failures, multiple threads accessed and modified shared data structures.
   I began by reviewing the code and identifying sections where multiple threads accessed shared resources without proper synchronization, debugged via IDE tool to see what is going on, and then I changed code to implement concurency control mechanism.
   ```
### Describe a scenario where you implemented a design pattern in a Java project. Which pattern did you use, and why was it the most suitable choice?
   - Explain the problem you were trying to solve.
   - Discuss the benefits of using the chosen design pattern in that specific context.
   - How did the design pattern improve the code structure, maintainability, or other aspects of the project?
   ```
   Factory method - we have several different database connection, different generation of report, different notification ways. This simplified the code and made it more scalable and adaptable to future changes.
   Builder - give ability to simplyfy creation of the object, we had a configuration class with a lot of different optional parameters.
   Command - there was a task that required to manage reports and perfom actions like edit, save, download. It gives flexibility, decoupling and easy way to test the flow.
   ```
### What experience do you have with AWS?
```
Over the past five years, I've been involved in the AWS ecosystem, crafting and launching applications from scratch all the way to production
Used: AWS VPC,IAM,Subnets,RDS,EC2,Batch,Lambda,ECR,ELB,ECS,WAF,Inspector and etc.
```

