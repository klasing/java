ECHO OFF

REM go from subdirirectory named tool to the project root directory
CD ..

REM set CLASSPATH to find resourcebundles
REM SET CLASSPATH=.;C:\Users\Klasing\MyProgramming\MyJava\Java2017\TestParseJSON\lib\json-lib-2.4-jdk15.jar
SET CLASSPATH=.;C:\Users\Klasing\MyProgramming\MyJava\Java2017\TestParseJSON\lib\java-json.jar

ECHO [COMPILE]
C:\Progra~1\Java\jdk1.8.0_112\bin\javac src\*.java

ECHO [DEPLOY]
IF NOT EXIST build\src MKDIR build\src
IF NOT EXIST build\src\resource MKDIR build\src\resource
MOVE src\*.class build\src
COPY src\resource\*.properties build\src\resource

REM go from the project root directory to the subdirectory named build
CD build

ECHO [RUN]
C:\Progra~1\Java\jdk1.8.0_112\bin\java src.TestParseJSON nl NL
REM C:\Progra~1\Java\jdk1.8.0_112\bin\java src.TestParseJSON en US

REM go from the subdirectory named build to the subdirectory named tool
CD ..\tool