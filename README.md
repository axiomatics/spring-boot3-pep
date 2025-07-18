# Axiomatics Spring Boot 3 demo
An example of how to inject a Policy Enforcement Point (PEP) in a Spring Boot application.
This example uses Spring interceptors. Security filter or Spring AOP may also be used.

## Annotation-based interceptor

The PEP is injected by an interceptor that checks for @AuthorizeWithAxiomaticsPDP annotation on a Controller class or method.
This means you can add this annotations when you would like to call the PDP and have externalized authorization via Axiomatics PDP:
```java
@RestController
@RequestMapping("/hello")
@AuthorizeWithAxiomaticsPDP
public class HelloController {

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        //Before executing the method, authorization will be done by PDP, becuase of the @AuthorizeWithAxiomaticsPDP annotation on the controller class.
        return "Hello " + name;
    }
}
```


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