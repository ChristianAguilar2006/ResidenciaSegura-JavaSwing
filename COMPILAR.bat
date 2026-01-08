@echo off
echo ========================================
echo   RESIDENCIA SEGURA - Compilar Proyecto
echo ========================================
echo.

REM Crear carpeta build si no existe
if not exist build mkdir build
if not exist lib mkdir lib

echo Compilando proyecto...
echo.

REM Compilar todos los archivos Java
javac -encoding UTF-8 -d build -cp "src" src/com/residenciasegura/modelo/*.java src/com/residenciasegura/util/*.java src/com/residenciasegura/dao/*.java src/com/residenciasegura/controlador/*.java src/com/residenciasegura/vista/*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo   COMPILACION EXITOSA!
    echo ========================================
    echo.
    echo Archivos compilados en la carpeta: build
    echo.
    echo NOTA IMPORTANTE:
    echo Para ejecutar necesitas:
    echo 1. El driver de MySQL (mysql-connector-java.jar)
    echo    Descárgalo de: https://dev.mysql.com/downloads/connector/j/
    echo    Colócalo en la carpeta 'lib'
    echo.
    echo 2. MySQL debe estar corriendo
    echo 3. La base de datos ResidenciaSegura debe existir
    echo.
) else (
    echo.
    echo ========================================
    echo   ERROR EN LA COMPILACION
    echo ========================================
    echo.
    echo Revisa los errores arriba.
    echo.
)

pause

