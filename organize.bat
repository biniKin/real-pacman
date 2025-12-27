@echo off
echo ========================================
echo    ORGANIZING PROJECT FILES
echo ========================================
echo.

REM Create directories if they don't exist
if not exist "src" mkdir src
if not exist "bin" mkdir bin
if not exist "docs" mkdir docs
if not exist "docs\abilities" mkdir "docs\abilities"

echo Creating folder structure...
echo - src/          (source code)
echo - bin/          (compiled files)
echo - docs/         (documentation)
echo - docs/abilities/ (ability docs)
echo.

REM Move Java source files to src
echo Moving Java source files to src/...
if exist "*.java" move /Y "*.java" "src\" >nul 2>&1

REM Move class files to bin
echo Moving compiled class files to bin/...
if exist "*.class" move /Y "*.class" "bin\" >nul 2>&1

REM Move ability documentation to docs/abilities
echo Moving ability documentation to docs/abilities/...
if exist "SLOW_MOTION_ABILITY.md" move /Y "SLOW_MOTION_ABILITY.md" "docs\abilities\" >nul 2>&1
if exist "GHOST_FREEZE_ABILITY.md" move /Y "GHOST_FREEZE_ABILITY.md" "docs\abilities\" >nul 2>&1
if exist "COIN_MAGNET_ABILITY.md" move /Y "COIN_MAGNET_ABILITY.md" "docs\abilities\" >nul 2>&1

REM Move other documentation to docs
echo Moving documentation files to docs/...
if exist "README.md" move /Y "README.md" "docs\" >nul 2>&1
if exist "MAZE_GENERATION.md" move /Y "MAZE_GENERATION.md" "docs\" >nul 2>&1

echo.
echo ========================================
echo    ORGANIZATION COMPLETE!
echo ========================================
echo.
echo Project structure:
echo   src/          - All .java source files
echo   bin/          - All .class compiled files
echo   docs/         - Main documentation
echo   docs/abilities/ - Ability documentation
echo   Root/         - Scripts and quick guides
echo.
echo You can now run the game with: run.bat
echo.
pause
