@echo on

REM Répertoire du projet
set PROJECT_DIR=C:\Users\Tsiky\Documents\GitHub\crud
set SRC_DIR=%PROJECT_DIR%\src
set BUILD_DIR=%PROJECT_DIR%\build
set WEB_DIR=%PROJECT_DIR%\web
set CLASSES_DIR=%WEB_DIR%\WEB-INF\classes
set LIB_DIR=%PROJECT_DIR%\lib
set MODEL_DIR=%PROJECT_DIR%\model
set TOMCAT_DIR=C:\Program Files\Apache Software Foundation\Tomcat 10.1
set WAR_NAME=Cluster.war

REM Définition de CATALINA_HOME pour Tomcat
set CATALINA_HOME=%TOMCAT_DIR%
set PATH=%CATALINA_HOME%\bin;%PATH%

REM Configuration de JAVA_HOME et du PATH
set JAVA_HOME=C:\Program Files\Java\jdk-17.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

echo Vérification de la version Java...
java -version
if %errorlevel% neq 0 (
    echo "Erreur : Java 17 n'est pas installé ou JAVA_HOME est mal configuré."
    pause
    exit /b
)

REM Vérification du répertoire de Tomcat
if not exist "%TOMCAT_DIR%\webapps" (
    echo "Erreur : Le chemin vers Tomcat est incorrect."
    pause
    exit /b
)

REM Création des répertoires nécessaires
echo Création des répertoires nécessaires...
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"
if not exist "%CLASSES_DIR%" mkdir "%CLASSES_DIR%"

REM Compilation des fichiers Java
echo Compilation des fichiers Java avec Java 17...
cd "%SRC_DIR%"
javac -d "%CLASSES_DIR%" -cp "%LIB_DIR%\*" *.java
if %errorlevel% neq 0 (
    echo "Erreur lors de la compilation. Vérifiez votre code source."
    pause
    exit /b
)

REM Copie des fichiers web
echo Copie des fichiers web...
xcopy "%WEB_DIR%" "%BUILD_DIR%" /E /I /Y
if %errorlevel% neq 0 (
    echo "Erreur lors de la copie des fichiers web."
    pause
    exit /b
)

REM Copie des fichiers jar dans WEB-INF/lib
echo Copie des bibliothèques dans WEB-INF/lib...
if not exist "%WEB_DIR%\WEB-INF\lib" mkdir "%WEB_DIR%\WEB-INF\lib"
xcopy "%LIB_DIR%\*.jar" "%WEB_DIR%\WEB-INF\lib" /Y
if %errorlevel% neq 0 (
    echo "Erreur lors de la copie des fichiers jar."
    pause
    exit /b
)


REM Copie des fichiers jar dans WEB-INF/lib
echo Copie des bibliothèques dans WEB-INF/web..
if not exist "%WEB_DIR%\WEB-INF\web" mkdir "%WEB_DIR%\WEB-INF\web"
xcopy "%MODEL_DIR%\*.jar" "%WEB_DIR%\WEB-INF\WEB" /Y
if %errorlevel% neq 0 (
    echo "Erreur lors de la copie des fichiers jar."
    pause
    exit /b
)


REM Création de l'archive WAR
echo Création du fichier WAR...
cd "%BUILD_DIR%"
jar -cvf "%PROJECT_DIR%\%WAR_NAME%" *
if %errorlevel% neq 0 (
    echo "Erreur lors de la création de l'archive WAR."
    pause
    exit /b
)

REM Déploiement dans Tomcat
echo Déploiement dans Tomcat...
copy "%PROJECT_DIR%\%WAR_NAME%" "%TOMCAT_DIR%\webapps"
if %errorlevel% neq 0 (
    echo "Erreur lors du déploiement dans Tomcat."
    pause
    exit /b
)

echo Déploiement terminé avec succès.

REM Pause pour maintenir le terminal ouvert
pause
