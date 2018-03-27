ECHO OFF

REM go from subdirirectory named tool to the project root directory
CD ..

ECHO [COMPILE]
C:\Progra~1\Java\jdk1.8.0_112\bin\javac src\*.java

ECHO [DEPLOY]
IF NOT EXIST build\src MKDIR build\src
REM IF NOT EXIST build\src\resource MKDIR build\src\resource
MOVE src\*.class build\src
REM COPY src\resource\*.properties build\src\resource

REM go from the project root directory to the subdirectory named build
CD build

ECHO [RUN]
C:\Progra~1\Java\jdk1.8.0_112\bin\java src.HelloWorld_GUI

REM go from the subdirectory named build to the subdirectory named tool
CD ..\tool