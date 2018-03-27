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
uses Gradle to build, see:
https://spring.io/guides/gs/gradle/
build with gradle:
  gradle build
build with gradle wrapper:
  gradle wrapper --gradle-version 2.13
  continue with:
  gradlew build
  continue with:
  gradlew run
*/