@echo off
:MENU
set /P c=Se restaurara la BD, continuar? [Y/N]
if /I "%c%" EQU "Y" goto :CONTINUAR
if /I "%c%" EQU "N" goto :NOCONTINUAR
goto :MENU


:CONTINUAR
echo Importando la base de datos...
mysql -uroot -root -hlocalhost -P3306 -e"SET GLOBAL log_bin_trust_function_creators = 1"
mysql -uroot -root -hlocalhost -P3306 -e"DROP DATABASE incendiapp"
mysql -uroot -root -hlocalhost -P3306 -e"CREATE SCHEMA incendiapp"
echo Importacion finalizada
pause
exit

:NOCONTINUAR

echo No continuara la ejecucion
pause
exit