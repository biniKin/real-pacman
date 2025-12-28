@echo off
echo Compiling PacmanGame for Binyam...

:: Compile the code using your specific JavaFX path
javac --module-path "C:\Users\Binyam\Downloads\javafx-sdk-21.0.9\lib" --add-modules javafx.controls,javafx.fxml PacmanGame.java

if %errorlevel% == 0 (
    echo Compilation successful! Starting the game...
    
    :: Run the code using your specific JavaFX path
    java --module-path "C:\Users\Binyam\Downloads\javafx-sdk-21.0.9\lib" --add-modules javafx.controls,javafx.fxml PacmanGame
) else (
    echo.
    echo [ERROR] Compilation failed. 
    echo 1. Check if PacmanGame.java is in this folder.
    echo 2. Check if your JavaFX path is still correct.
    pause
)