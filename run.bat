@echo off
echo ========================================
echo    COMPILING PACMAN GAME
echo ========================================

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile all Java files from src to bin
javac --module-path "C:\javafx-sdk\lib" --add-modules javafx.controls -d bin src\Direction.java src\PacmanGame.java src\GameController.java src\Maze.java src\Pacman.java src\Ghost.java src\SoundManager.java

if %errorlevel% == 0 (
    echo.
    echo ========================================
    echo    COMPILATION SUCCESSFUL!
    echo    STARTING PACMAN...
    echo ========================================
    echo.
    
    REM Run the game from bin directory
    java --module-path "C:\javafx-sdk\lib" --add-modules javafx.controls -cp bin PacmanGame
) else (
    echo.
    echo ========================================
    echo    COMPILATION FAILED!
    echo ========================================
    echo Please check:
    echo 1. JavaFX SDK is installed
    echo 2. Path in this script is correct: C:\javafx-sdk\lib
    echo 3. Java version is 11 or higher
    echo 4. All source files are in src\ folder
    echo.
    pause
)
