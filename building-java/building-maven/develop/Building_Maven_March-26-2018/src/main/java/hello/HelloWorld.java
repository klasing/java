package hello;

import org.joda.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);

        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}
/*
uses Maven to build, see:
https://spring.io/guides/gs/maven/
build:
  mvn compile
build package:
  mvn package
run test:
  mvn test
*/
