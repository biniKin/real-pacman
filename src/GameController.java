import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Main game controller managing game state and logic
 */
public class GameController {
    
    private Pane root;
    private Scene scene;
    private Maze maze;
    private Pacman pacman;
    private List<Ghost> ghosts;
    private AnimationTimer gameLoop;
    
    // UI Elements
    private Text scoreText;
    private Text livesText;
    private Text levelText;
    private Rectangle background;
    
    // Game State
    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private boolean gameRunning = false;
    private boolean powerMode = false;
    private long powerModeStart = 0;
    private static final long POWER_MODE_DURATION = 8_000_000_000L;
    
    // Slow Motion Ability
    private boolean slowMotionActive = false;
    private long slowMotionStart = 0;
    private static final long SLOW_MOTION_DURATION = 5_000_000_000L; // 5 seconds
    private Text slowMotionText;
    
    // Ghost Freeze Ability
    private boolean ghostFreezeActive = false;
    private long ghostFreezeStart = 0;
    private static final long GHOST_FREEZE_DURATION = 4_000_000_000L; // 4 seconds
    private Text ghostFreezeText;
    
    // Coin Magnet Ability
    private boolean coinMagnetActive = false;
    private long coinMagnetStart = 0;
    private static final long COIN_MAGNET_DURATION = 7_000_000_000L; // 7 seconds
    private static final double COIN_MAGNET_RADIUS = 5.0; // 5 tiles radius
    private Text coinMagnetText;
    
    // Timing
    private long lastPacmanUpdate = 0;
    private long lastGhostUpdate = 0;
    private static final long PACMAN_MOVE_INTERVAL = 100_000_000L; // 100ms
    private long ghostMoveInterval = 200_000_000L; // 200ms initially
    
    public GameController(Pane root, Scene scene) {
        this.root = root;
        this.scene = scene;
        this.ghosts = new ArrayList<>();
    }
    
    /**
     * Shows the start menu
     */
    public void showStartMenu() {
        root.getChildren().clear();
        
        // Background
        Rectangle bg = new Rectangle(672, 744);
        bg.setFill(Color.BLACK);
        root.getChildren().add(bg);
        
        // Title
        Text title = new Text("PACMAN");
        title.setFill(Color.YELLOW);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        title.setX(220);
        title.setY(200);
        root.getChildren().add(title);
        
        // Instructions
        Text inst1 = new Text("Use Arrow Keys to Move");
        inst1.setFill(Color.WHITE);
        inst1.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        inst1.setX(220);
        inst1.setY(300);
        root.getChildren().add(inst1);
        
        Text inst2 = new Text("Eat all dots to win!");
        inst2.setFill(Color.WHITE);
        inst2.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        inst2.setX(240);
        inst2.setY(330);
        root.getChildren().add(inst2);
        
        Text inst3 = new Text("Avoid the ghosts!");
        inst3.setFill(Color.WHITE);
        inst3.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        inst3.setX(250);
        inst3.setY(360);
        root.getChildren().add(inst3);
        
        // Start button
        Button startButton = new Button("START GAME");
        startButton.setLayoutX(270);
        startButton.setLayoutY(420);
        startButton.setPrefWidth(140);
        startButton.setPrefHeight(50);
        startButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: black;");
        startButton.setOnAction(e -> startNewGame());
        root.getChildren().add(startButton);
    }
    
    /**
     * Starts a new game
     */
    public void startNewGame() {
        score = 0;
        lives = 3;
        level = 1;
        initializeLevel();
    }
    
    /**
     * Initializes a level
     */
    private void initializeLevel() {
        root.getChildren().clear();
        ghosts.clear();
        powerMode = false;
        slowMotionActive = false; // Reset slow motion on new level
        ghostFreezeActive = false; // Reset ghost freeze on new level
        coinMagnetActive = false; // Reset coin magnet on new level
        
        // Calculate ghost speed based on level
        ghostMoveInterval = Math.max(100_000_000L, 200_000_000L - (level - 1) * 20_000_000L);
        
        // Background
        background = new Rectangle(672, 744);
        background.setFill(Color.BLACK);
        root.getChildren().add(background);
        
        // Create maze
        maze = new Maze(level);
        root.getChildren().add(maze.getMazeGroup());
        
        // Create Pacman
        pacman = new Pacman(maze.getPacmanStartX(), maze.getPacmanStartY());
        root.getChildren().add(pacman.getSprite());
        
        // Create ghosts
        createGhosts();
        
        // Setup UI
        setupUI();
        
        // Setup controls
        setupControls();
        
        // Start game
        gameRunning = true;
        startGameLoop();
    }
    
    /**
     * Creates ghosts for the level
     */
    private void createGhosts() {
        Color[] colors = {Color.RED, Color.PINK, Color.CYAN, Color.ORANGE};
        int[][] starts = {{13, 11}, {14, 11}, {13, 12}, {14, 12}};
        
        int numGhosts = Math.min(3 + (level - 1), 4);
        
        for (int i = 0; i < numGhosts; i++) {
            Ghost ghost = new Ghost(starts[i][0], starts[i][1], colors[i], maze, pacman);
            ghosts.add(ghost);
            root.getChildren().add(ghost.getSprite());
        }
    }
    
    /**
     * Sets up UI elements
     */
    private void setupUI() {
        scoreText = new Text(20, 30, "SCORE: 0");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(scoreText);
        
        livesText = new Text(250, 30, "LIVES: 3");
        livesText.setFill(Color.WHITE);
        livesText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(livesText);
        
        levelText = new Text(480, 30, "LEVEL: 1");
        levelText.setFill(Color.WHITE);
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(levelText);
        
        // Slow Motion UI (initially hidden)
        slowMotionText = new Text(250, 700, "");
        slowMotionText.setFill(Color.MAGENTA);
        slowMotionText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(slowMotionText);
        
        // Ghost Freeze UI (initially hidden)
        ghostFreezeText = new Text(250, 670, "");
        ghostFreezeText.setFill(Color.CYAN);
        ghostFreezeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(ghostFreezeText);
        
        // Coin Magnet UI (initially hidden)
        coinMagnetText = new Text(250, 640, "");
        coinMagnetText.setFill(Color.GOLD);
        coinMagnetText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(coinMagnetText);
    }
    
    /**
     * Sets up keyboard controls
     */
    private void setupControls() {
        scene.setOnKeyPressed(event -> {
            if (!gameRunning) return;
            
            KeyCode key = event.getCode();
            
            switch (key) {
                case UP:
                    pacman.setNextDirection(Direction.UP);
                    break;
                case DOWN:
                    pacman.setNextDirection(Direction.DOWN);
                    break;
                case LEFT:
                    pacman.setNextDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    pacman.setNextDirection(Direction.RIGHT);
                    break;
            }
        });
    }
    
    /**
     * Starts the game loop
     */
    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameRunning) return;
                
                // Update Pacman
                if (now - lastPacmanUpdate >= PACMAN_MOVE_INTERVAL) {
                    updatePacman();
                    lastPacmanUpdate = now;
                }
                
                // Update Ghosts (with slow motion adjustment and freeze check)
                if (!ghostFreezeActive) {
                    long currentGhostInterval = slowMotionActive ? ghostMoveInterval * 2 : ghostMoveInterval;
                    if (now - lastGhostUpdate >= currentGhostInterval) {
                        updateGhosts();
                        lastGhostUpdate = now;
                    }
                }
                
                // Check power mode timeout
                if (powerMode && now - powerModeStart >= POWER_MODE_DURATION) {
                    powerMode = false;
                    for (Ghost ghost : ghosts) {
                        ghost.setVulnerable(false);
                    }
                }
                
                // Check slow motion timeout
                if (slowMotionActive && now - slowMotionStart >= SLOW_MOTION_DURATION) {
                    deactivateSlowMotion();
                }
                
                // Check ghost freeze timeout
                if (ghostFreezeActive && now - ghostFreezeStart >= GHOST_FREEZE_DURATION) {
                    deactivateGhostFreeze();
                }
                
                // Check coin magnet timeout
                if (coinMagnetActive && now - coinMagnetStart >= COIN_MAGNET_DURATION) {
                    deactivateCoinMagnet();
                }
                
                // Update slow motion UI
                if (slowMotionActive) {
                    long remaining = (SLOW_MOTION_DURATION - (now - slowMotionStart)) / 1_000_000_000L;
                    slowMotionText.setText("SLOW MOTION: " + (remaining + 1) + "s");
                }
                
                // Update ghost freeze UI
                if (ghostFreezeActive) {
                    long remaining = (GHOST_FREEZE_DURATION - (now - ghostFreezeStart)) / 1_000_000_000L;
                    ghostFreezeText.setText("GHOST FREEZE: " + (remaining + 1) + "s");
                }
                
                // Update coin magnet UI
                if (coinMagnetActive) {
                    long remaining = (COIN_MAGNET_DURATION - (now - coinMagnetStart)) / 1_000_000_000L;
                    coinMagnetText.setText("COIN MAGNET: " + (remaining + 1) + "s");
                }
                
                // Apply coin magnet effect
                if (coinMagnetActive) {
                    maze.attractDotsToPosition(pacman.getGridX(), pacman.getGridY(), COIN_MAGNET_RADIUS);
                }
                
                // Update animations
                pacman.updateAnimation();
            }
        };
        gameLoop.start();
    }
    
    /**
     * Updates Pacman position and handles collisions
     */
    private void updatePacman() {
        // Try to move Pacman
        pacman.move(maze);
        
        // Check dot collection
        if (maze.hasDot(pacman.getGridX(), pacman.getGridY())) {
            maze.removeDot(pacman.getGridX(), pacman.getGridY());
            score += 10;
            updateUI();
            
            // Check level completion
            if (maze.allDotsCollected()) {
                levelComplete();
            }
        }
        
        // Check power pellet collection
        if (maze.hasPowerPellet(pacman.getGridX(), pacman.getGridY())) {
            maze.removePowerPellet(pacman.getGridX(), pacman.getGridY());
            score += 50;
            activatePowerMode();
            updateUI();
        }
        
        // Check slow motion power-up collection
        if (maze.hasSlowMotionPowerUp(pacman.getGridX(), pacman.getGridY())) {
            maze.removeSlowMotionPowerUp(pacman.getGridX(), pacman.getGridY());
            score += 100;
            activateSlowMotion();
            updateUI();
        }
        
        // Check ghost freeze power-up collection
        if (maze.hasGhostFreezePowerUp(pacman.getGridX(), pacman.getGridY())) {
            maze.removeGhostFreezePowerUp(pacman.getGridX(), pacman.getGridY());
            score += 150;
            activateGhostFreeze();
            updateUI();
        }
        
        // Check coin magnet power-up collection
        if (maze.hasCoinMagnetPowerUp(pacman.getGridX(), pacman.getGridY())) {
            maze.removeCoinMagnetPowerUp(pacman.getGridX(), pacman.getGridY());
            score += 200;
            activateCoinMagnet();
            updateUI();
        }
        
        pacman.updateAnimation();
    }
    
    /**
     * Updates all ghosts
     */
    private void updateGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move();
            
            // Check collision with Pacman
            if (ghost.getGridX() == pacman.getGridX() && ghost.getGridY() == pacman.getGridY()) {
                if (powerMode && ghost.isVulnerable()) {
                    // Pacman eats ghost
                    score += 200;
                    ghost.respawn();
                    updateUI();
                } else if (!ghost.isVulnerable()) {
                    // Ghost catches Pacman
                    pacmanCaught();
                }
            }
        }
    }
    
    /**
     * Activates power mode
     */
    private void activatePowerMode() {
        powerMode = true;
        powerModeStart = System.nanoTime();
        
        for (Ghost ghost : ghosts) {
            ghost.setVulnerable(true);
        }
    }
    
    /**
     * Activates slow motion ability
     */
    private void activateSlowMotion() {
        slowMotionActive = true;
        slowMotionStart = System.nanoTime();
        slowMotionText.setText("SLOW MOTION: 5s");
    }
    
    /**
     * Deactivates slow motion ability
     */
    private void deactivateSlowMotion() {
        slowMotionActive = false;
        slowMotionText.setText("");
    }
    
    /**
     * Activates ghost freeze ability
     */
    private void activateGhostFreeze() {
        ghostFreezeActive = true;
        ghostFreezeStart = System.nanoTime();
        ghostFreezeText.setText("GHOST FREEZE: 4s");
    }
    
    /**
     * Deactivates ghost freeze ability
     */
    private void deactivateGhostFreeze() {
        ghostFreezeActive = false;
        ghostFreezeText.setText("");
    }
    
    /**
     * Activates coin magnet ability
     */
    private void activateCoinMagnet() {
        coinMagnetActive = true;
        coinMagnetStart = System.nanoTime();
        coinMagnetText.setText("COIN MAGNET: 7s");
    }
    
    /**
     * Deactivates coin magnet ability
     */
    private void deactivateCoinMagnet() {
        coinMagnetActive = false;
        coinMagnetText.setText("");
    }
    
    /**
     * Handles Pacman being caught
     */
    private void pacmanCaught() {
        lives--;
        
        if (lives <= 0) {
            gameOver();
        } else {
            // Reset positions
            gameRunning = false;
            
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                pacman.reset(maze.getPacmanStartX(), maze.getPacmanStartY());
                for (Ghost ghost : ghosts) {
                    ghost.respawn();
                }
                powerMode = false;
                slowMotionActive = false; // Reset slow motion on death
                slowMotionText.setText("");
                ghostFreezeActive = false; // Reset ghost freeze on death
                ghostFreezeText.setText("");
                coinMagnetActive = false; // Reset coin magnet on death
                coinMagnetText.setText("");
                updateUI();
                gameRunning = true;
            });
            pause.play();
        }
    }
    
    /**
     * Handles level completion
     */
    private void levelComplete() {
        gameRunning = false;
        gameLoop.stop();
        
        score += 1000;
        updateUI();
        
        // Show level complete message
        Text message = new Text(200, 372, "LEVEL COMPLETE!");
        message.setFill(Color.YELLOW);
        message.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        root.getChildren().add(message);
        
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            level++;
            initializeLevel();
        });
        pause.play();
    }
    
    /**
     * Handles game over
     */
    private void gameOver() {
        gameRunning = false;
        gameLoop.stop();
        
        // Darken screen
        Rectangle overlay = new Rectangle(672, 744);
        overlay.setFill(Color.color(0, 0, 0, 0.7));
        root.getChildren().add(overlay);
        
        // Game over text
        Text gameOverText = new Text(220, 300, "GAME OVER"); // text takes x,y for position of baseline start
        gameOverText.setFill(Color.RED);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        root.getChildren().add(gameOverText);
        
        Text finalScore = new Text(230, 360, "FINAL SCORE: " + score);
        finalScore.setFill(Color.WHITE);
        finalScore.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(finalScore);
        
        // Restart button
        Button restartButton = new Button("PLAY AGAIN");
        restartButton.setLayoutX(270);
        restartButton.setLayoutY(400);
        restartButton.setPrefWidth(140);
        restartButton.setPrefHeight(50);
        restartButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: black;");
        restartButton.setOnAction(e -> startNewGame());
        root.getChildren().add(restartButton);
    }
    
    /**
     * Updates UI elements
     */
    private void updateUI() {
        scoreText.setText("SCORE: " + score);
        livesText.setText("LIVES: " + lives);
        levelText.setText("LEVEL: " + level);
    }
}