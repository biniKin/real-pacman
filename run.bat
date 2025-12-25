@echo off
echo Compiling 3D Pacman Game...
javac --module-path "D:\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml *.java

if %errorlevel% == 0 (
    echo Compilation successful! Running game...
    java --module-path "D:\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml PacmanGame
) else (
    echo Compilation failed. Please check your JavaFX installation.
    pause
)