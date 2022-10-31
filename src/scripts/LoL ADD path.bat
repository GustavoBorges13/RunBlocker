
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
@echo off
setlocal enabledelayedexpansion
 
cls
echo ----------------------------------------------
echo LoL ADD path by Gustavo Borges (https://github.com/GustavoBorges13)
echo ----------------------------------------------
 
:: Get LoL Directory Path
:getPath
set /p "lolpath=What path is League of Legends installed in? [Example: C:\Riot Games\League of Legends]: "
 
:: Check for valid path
if ["%lolpath%"] == [] (
  echo Empty path is not allowed.
  goto getPath
)
 
if not exist "%lolpath%" (
  echo Path points to folder that does not exist.
  goto getPath
)
 
:: Add LoL path to Windows Registers
set lolpath=%lolpath%
 echo Key values will be set to the following path: %lolpath% & echo.

 echo Fixing key: HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\LeagueClient.exe
 reg add "HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\LeagueClient.exe" /ve /d "%lolpath%\LeagueClient.exe" /f & echo.

 echo Fixing key: HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\LeagueClient.exe
 reg add "HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\LeagueClient.exe" /v Path /t REG_SZ /d "%lolpath%" /f && (
 echo.
 echo Success!!! All keys fixed!
 pause
) || (
 echo.
 echo You need to execute this .bat with administrator permissions.
 pause
)