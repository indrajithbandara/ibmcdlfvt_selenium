set JAVAC5_HOME=c:\jdk1.5.0\bin\javac
set JAVA5_HOME=java

set CPATH=.;testng-2.0beta-jdk15.jar;commons-lang-2.0.jar;src/

%JAVAC5_HOME% -classpath %CPATH% src\tests\*.java
%JAVA5_HOME% -ea -classpath %CPATH% com.beust.testng.TestNG src/testng.xml