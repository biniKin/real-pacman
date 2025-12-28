import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main entry point for the Pacman game
 */
public class PacmanGame extends Application {
    
    private static final int WINDOW_WIDTH = 672;  // 28 tiles * 24 pixels
    private static final int WINDOW_HEIGHT = 744; // 31 tiles * 24 pixels
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // Initialize game controller
        GameController gameController = new GameController(root, scene);
        gameController.showStartMenu();
        
        primaryStage.setTitle("PACMAN");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        root.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
