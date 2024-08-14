@echo off

set JAVA_HOME=C:\psy\dev\jdk\17

set PATH=%JAVA_HOME%\bin;%PATH%

call mvn --settings settings.xml -Prelease clean deploy
