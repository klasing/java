ECHO OFF

SET JAVA_HOME=C:\Progra~1\Java\jdk1.8.0_112\bin\

CD ..
SET CLASSPATH=
SET CLASSPATH=.\build;.\lib\jackson-annotations-2.8.0.jar;.\lib\jackson-core-2.8.1.jar;.\lib\jackson-databind-2.8.5.jar
SET CLASSPATH=%CLASSPATH%;.\lib\google-api-services-drive-v3-rev55-1.22.0.jar;.\lib\google-oauth-client-java6-1.22.0.jar;.\lib\google-oauth-client-jetty-1.22.0.jar;.\lib\google-api-client-1.22.0.jar;.\lib\jetty-6.1.26.jar;.\lib\jetty-util-6.1.26.jar
SET CLASSPATH=%CLASSPATH%;.\lib\google-api-client-1.22.0-sources.jar;.\lib\jackson-core-2.1.3.jar;.\lib\google-http-client-1.22.0.jar;.\lib\google-http-client-jackson2-1.22.0.jar;.\lib\google-oauth-client-1.22.0.jar;.\lib\servlet-api-2.5.jar

ECHO [COMPILE]
%JAVA_HOME%javac src\*.java

ECHO [DEPLOY]
MOVE src\*.class build\src
COPY src\resource\*.properties build\src\resource

ECHO [RUN]
%JAVA_HOME%java src.Frame4WeekJson nl NL

CD tool
