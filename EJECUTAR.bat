@echo off
echo ========================================
echo   RESIDENCIA SEGURA - Ejecutar Proyecto
echo ========================================
echo.

REM Verificar si Java está instalado
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java no encontrado en el PATH
    echo.
    echo Por favor ejecuta el proyecto desde NetBeans:
    echo 1. Abre NetBeans
    echo 2. Abre este proyecto
    echo 3. Click derecho en VentanaLogin.java
    echo 4. Selecciona "Run File"
    echo.
    pause
    exit /b 1
)

echo Java encontrado!
echo.

REM Compilar el proyecto
echo Compilando proyecto...
javac -encoding UTF-8 -d build -cp "src;lib/*" src/com/residenciasegura/**/*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR al compilar. Verifica los errores arriba.
    echo.
    echo NOTA: Necesitas el driver de MySQL (mysql-connector-java.jar)
    echo Descárgalo de: https://dev.mysql.com/downloads/connector/j/
    echo Y colócalo en la carpeta 'lib'
    echo.
    pause
    exit /b 1
)

echo.
echo Compilación exitosa!
echo.
echo Ejecutando aplicación...
echo.

REM Ejecutar la aplicación
java -cp "build;lib/*" com.residenciasegura.vista.VentanaLogin

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR al ejecutar. Verifica:
    echo 1. Que MySQL esté corriendo
    echo 2. Que la base de datos ResidenciaSegura exista
    echo 3. Que el driver de MySQL esté en la carpeta lib
    echo.
    pause
    exit /b 1
)

pause

