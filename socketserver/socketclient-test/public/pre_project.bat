ECHO OFF

ECHO [MKDIR]
MKDIR src\main\java\pack
MKDIR src\main\resources
MKDIR src\test\java

ECHO [COPY]
COPY ..\build.gradle build.gradle
COPY ..\log4j.properties src\main\resources\log4j.properties
COPY ..\Main.java src\main\java\pack\Main.java

ECHO [WRAPPER]
REM Google Drive requires Gradle 2.3, instead of Gradle 2.13
gradle wrapper  --gradle-version 2.3

ECHO [REMOVE]
DEL pre_project.bat