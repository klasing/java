ECHO OFF

IF EXIST build GOTO skip_build
MKDIR build
MKDIR build\src

:skip_build
IF NOT EXIST jar MKDIR jar
IF NOT EXIST lib MKDIR lib
IF EXIST src GOTO skip_src
MKDIR src
MKDIR src\resource

:skip_src
IF EXIST tool GOTO skip_tool
MKDIR tool
MKDIR tool\ant

:skip_tool
MOVE pro.bat tool
