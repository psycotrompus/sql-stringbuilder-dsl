@echo off

call mvn --settings settings.xml -Prelease clean deploy
