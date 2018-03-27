package hello;

import org.apache.log4j.Logger;
//import org.apache.log4j.BasicConfigurator;

public class HelloWorld {
    static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        //System.out.println("Hello Ant World!");
        //BasicConfigurator.configure();
        logger.debug("Hello Ant World!");
        logger.info("Hello Ant World!");
    }
}
/*
uses Ant to build, see:
http://ant.apache.org/manual/tutorial-HelloWorldWithAnt.html
build manualy:
  MKDIR build\classes
  javac -sourcepath src -d build\classes src\hello\HelloWorld.java
  java -cp build\classes hello.HelloWorld
build executable jar:
  ECHO Main-Class: hello.HelloWorld>myManifest
  MKDIR build\jar
  jar cfm build\jar\HelloWorld.jar myManifest -C build\classes .
  (execute jar)
  java -jar build\jar\HelloWorld.jar
ant build:
  ant compile jar run
enhanced build, external library, configuration files:
  ant
testing the class:
  ant junit
*/