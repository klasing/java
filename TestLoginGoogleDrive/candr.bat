ECHO OFF

SET JAVA_HOME=C:\Progra~1\Java\jdk1.8.0_112\bin\

SET CLASSPATH=
SET CLASSPATH=%CLASSPATH%;.\google-api-services-drive-v3-rev55-1.22.0.jar;.\google-oauth-client-java6-1.22.0.jar;.\google-oauth-client-jetty-1.22.0.jar;.\google-api-client-1.22.0.jar;.\jetty-6.1.26.jar;.\jetty-util-6.1.26.jar
SET CLASSPATH=%CLASSPATH%;.\google-api-client-1.22.0-sources.jar;.\jackson-core-2.1.3.jar;.\google-http-client-1.22.0.jar;.\google-http-client-jackson2-1.22.0.jar;.\google-oauth-client-1.22.0.jar;.\java-json.jar

%JAVA_HOME%javac *.java
%JAVA_HOME%java MyGoogleDrive
