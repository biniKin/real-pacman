import javafx.application.Application; // base class for javafx applications, provides lifecycle methods like start(). Every javafx app must extend this class.
import javafx.scene.Scene; // represents everything inside stage(window). It holds nodes and manages rendering.
import javafx.scene.layout.Pane; // a simple layout container which can hold and position child nodes. using coordinates.
import javafx.stage.Stage; // Represents the actual application window

public class Game extends Application {
    // final is for constants per object, static final is for class-wide constants
    private static final int WINDOW_WIDTH = 800; // immutable value within class scope
    private static final int WINDOW_HEIGHT = 600;
    
    private GameController gameController;
    
    @Override
    public void start(Stage primaryStage) { // lifecycle method called when app is launched. entry point for javafx apps.
        Pane root = new Pane(); // root node of scene graph, holds all visual elements. like canvas.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        gameController = new GameController(root, scene); // root for adding game elements, scene for input handling
        gameController.startNewGame();
        
        primaryStage.setTitle("2D Pacman Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();    
        
        // Request focus for key events
        root.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args); // launches the javafx application, calls start() method
    }
}