if not exist ".\build\" mkdir .\build
cd .\src
javac -d ..\build *.java
cd ..
if not exist ".\dist\" mkdir .\dist
jar cfev dist\FileVibe.jar filevibe.FileVibe -C .\build\ filevibe
start explorer .\dist
pause