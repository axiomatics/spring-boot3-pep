# Axiomatics Spring Boot 3 demo
An example of how to inject a Policy Enforcement Point (PEP) in a Spring Boot application.
This example uses Spring interceptors. Security filter or Spring AOP may also be used.

## /demo-app service
A simple Spring Boot service. Invoke /hello/${name}`, and it returns 'Hello ${name}'.

The serivce [HelloController.java](demo-app/src/main/java/com/axiomatics/demo/app/controller/HelloController.java) 
has annotation @AuthorizeWithAxiomaticsPDP, which means the Axiomatics PDP will be called when invoking this service

## How to run

A PDP is not included in this demo, but is expected to already be available.
Configure [application.yaml](demo-app/src/main/resources/application.yaml) with connection deatils.

`gradlew demo-app:bootRun` to start application and then browse
[http://127.0.0.1:8080/hello/World!](http://127.0.0.1:8080/hello/World!)

Login with user `alice` and password `password`.