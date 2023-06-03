@echo off
:MENU
set /P c=Se restaurara la BD, continuar? [Y/N]
if /I "%c%" EQU "Y" goto :CONTINUAR
if /I "%c%" EQU "N" goto :NOCONTINUAR
goto :MENU


:CONTINUAR
echo Importando la base de datos...
cd C:\Program Files\MySQL\MySQL Server 5.7\bin
mysql -uroot -proot -hlocalhost -P3306 -e"SET GLOBAL log_bin_trust_function_creators = 1"
mysql.exe -uroot -proot -hlocalhost -P3306 -e"DROP DATABASE incendiapp"
mysql.exe -uroot -proot -hlocalhost -P3306 -e"CREATE SCHEMA incendiapp"
echo Para cargar las tablas, ejecutar el proyecto
echo Importacion finalizada
pause
exit

:NOCONTINUAR

echo No continuara la ejecucion
pause
exit