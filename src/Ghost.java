// import javafx.scene.Group; // used to group multiple nodes or shapes together
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Polygon;
// import javafx.scene.shape.Rectangle;
// import java.util.ArrayList; // for storing valid directions
// import java.util.List; // for storing valid directions
// import java.util.Random; // for random direction choices for the ghost

// /**
//  * Ghost with smooth AI movement
//  */
// public class Ghost {
    
//     private Group sprite; // Ghost visual representation
//     private int gridX;
//     private int gridY;
//     private int startX; // starting position
//     private int startY;
//     private Direction currentDirection; // current movement direction
//     private Color ghostColor;
//     // References
//     private Maze maze; // maze for wall detection
//     private Pacman pacman; // pacman for chasing/fleeing
//     private Random random; // for random direction choices
    
//     // for state
//     private boolean vulnerable = false; // after pacman eats power pellet
//     private int moveCounter = 0; // counter to control movement speed
    
//     public Ghost(int startX, int startY, Color color, Maze maze, Pacman pacman) {
//         this.startX = startX;
//         this.startY = startY;
//         this.gridX = startX;
//         this.gridY = startY;
//         this.ghostColor = color;
//         this.maze = maze;
//         this.pacman = pacman;
//         this.random = new Random();
//         this.currentDirection = Direction.LEFT;
        
//         createSprite();
//         updatePosition();
//     }
    
//     /**
//      * Creates ghost sprite
//      */
//     private void createSprite() {
//         sprite = new Group();
        
//         // Body
//         Rectangle body = new Rectangle(18, 18);
//         body.setFill(ghostColor);
//         body.setStroke(Color.DARKGRAY);
//         body.setStrokeWidth(1);
//         body.setX(-9); // center the body
//         body.setY(-9); // center the body
        
//         // Bottom wavy part
//         Polygon bottom = new Polygon();
//         bottom.getPoints().addAll(
//             -9.0, 9.0,
//             -6.0, 6.0,
//             -3.0, 9.0,
//             0.0, 6.0,
//             3.0, 9.0,
//             6.0, 6.0,
//             9.0, 9.0,
//             9.0, 0.0,
//             -9.0, 0.0
//         );
//         bottom.setFill(ghostColor);
//         bottom.setStroke(Color.DARKGRAY);
//         bottom.setStrokeWidth(1);
        
//         // Eyes
//         Circle leftEye = new Circle(-4, -3, 2.5);
//         leftEye.setFill(Color.WHITE);
//         Circle rightEye = new Circle(4, -3, 2.5);
//         rightEye.setFill(Color.WHITE);
        
//         Circle leftPupil = new Circle(-4, -3, 1.5);
//         leftPupil.setFill(Color.BLACK);
//         Circle rightPupil = new Circle(4, -3, 1.5);
//         rightPupil.setFill(Color.BLACK);
        
//         sprite.getChildren().addAll(body, bottom, leftEye, rightEye, leftPupil, rightPupil);
//     }
    
//     /**
//      * Moves ghost with AI
//      */
//     public void move() {
//         moveCounter++;
        
//         // Choose direction every few moves
//         if (moveCounter >= 3 || shouldChangeDirection()) {
//             currentDirection = chooseDirection();
//             moveCounter = 0;
//         }
        
//         // Move in current direction
//         int newX = gridX + currentDirection.getDeltaX();
//         int newY = gridY + currentDirection.getDeltaY();
        
//         // Handle tunnel wrapping
//         if (newX < 0) {
//             newX = Maze.WIDTH - 1;
//         } else if (newX >= Maze.WIDTH) {
//             newX = 0;
//         }
        
//         // Only move if not hitting wall
//         if (!maze.isWall(newX, newY)) {
//             gridX = newX;
//             gridY = newY;
//         } else {
//             // Hit wall, force direction change
//             currentDirection = chooseDirection();
//         }
        
//         updatePosition();
//     }
    
//     /**
//      * Checks if ghost should change direction
//      */
//     private boolean shouldChangeDirection() {
//         int nextX = gridX + currentDirection.getDeltaX();
//         int nextY = gridY + currentDirection.getDeltaY();
        
//         if (nextX < 0) nextX = Maze.WIDTH - 1;
//         if (nextX >= Maze.WIDTH) nextX = 0;
        
//         return maze.isWall(nextX, nextY);
//     }
    
//     /**
//      * Chooses direction using AI
//      */
//     private Direction chooseDirection() {
//         List<Direction> validDirections = getValidDirections();
        
//         if (validDirections.isEmpty()) {
//             return currentDirection;
//         }
        
//         if (vulnerable) {
//             return chooseFleeDirection(validDirections);
//         } else {
//             return chooseChaseDirection(validDirections);
//         }
//     }
    
//     /**
//      * Gets all valid directions
//      */
//     private List<Direction> getValidDirections() {
//         List<Direction> valid = new ArrayList<>(); // List is interface, ArrayList is implementation
        
//         for (Direction dir : Direction.values()) {
//             int newX = gridX + dir.getDeltaX();
//             int newY = gridY + dir.getDeltaY();
            
//             if (newX < 0) newX = Maze.WIDTH - 1;
//             if (newX >= Maze.WIDTH) newX = 0;
            
//             if (!maze.isWall(newX, newY)) {
//                 valid.add(dir);
//             }
//         }
        
//         return valid;
//     }
    
//     /**
//      * Chooses direction to chase Pacman
//      */
//     private Direction chooseChaseDirection(List<Direction> validDirections) {
//         int pacX = pacman.getGridX();
//         int pacY = pacman.getGridY();
        
//         Direction best = validDirections.get(0);
//         double bestDist = Double.MAX_VALUE;
        
//         for (Direction dir : validDirections) {
//             int newX = gridX + dir.getDeltaX();
//             int newY = gridY + dir.getDeltaY();
            
//             if (newX < 0) newX = Maze.WIDTH - 1;
//             if (newX >= Maze.WIDTH) newX = 0;
            
//             double dist = Math.sqrt(Math.pow(newX - pacX, 2) + Math.pow(newY - pacY, 2));
            
//             if (dist < bestDist) {
//                 bestDist = dist;
//                 best = dir;
//             }
//         }
        
//         // Add randomness
//         if (random.nextDouble() < 0.2) {
//             return validDirections.get(random.nextInt(validDirections.size()));
//         }
        
//         return best;
//     }
    
//     /**
//      * Chooses direction to flee from Pacman
//      */
//     private Direction chooseFleeDirection(List<Direction> validDirections) {
//         int pacX = pacman.getGridX();
//         int pacY = pacman.getGridY();
        
//         Direction best = validDirections.get(0);
//         double bestDist = 0;
        
//         for (Direction dir : validDirections) {
//             int newX = gridX + dir.getDeltaX();
//             int newY = gridY + dir.getDeltaY();
            
//             if (newX < 0) newX = Maze.WIDTH - 1;
//             if (newX >= Maze.WIDTH) newX = 0;
            
//             double dist = Math.sqrt(Math.pow(newX - pacX, 2) + Math.pow(newY - pacY, 2));
            
//             if (dist > bestDist) {
//                 bestDist = dist;
//                 best = dir;
//             }
//         }
        
//         return best;
//     }
    
//     /**
//      * Updates sprite position
//      */
//     private void updatePosition() {
//         double pixelX = gridX * Maze.TILE_SIZE + Maze.TILE_SIZE / 2;
//         double pixelY = gridY * Maze.TILE_SIZE + Maze.TILE_SIZE / 2;
        
//         sprite.setTranslateX(pixelX);
//         sprite.setTranslateY(pixelY);
//     }
    
//     /**
//      * Sets vulnerable state
//      */
//     public void setVulnerable(boolean vulnerable) {
//         this.vulnerable = vulnerable;
        
//         Color color = vulnerable ? Color.BLUE : ghostColor;
        
//         if (sprite.getChildren().size() >= 2) {
//             ((Rectangle) sprite.getChildren().get(0)).setFill(color);
//             ((Polygon) sprite.getChildren().get(1)).setFill(color);
//         }
//     }
    
//     /**
//      * Respawns ghost at start position
//      */
//     public void respawn() {
//         this.gridX = startX;
//         this.gridY = startY;
//         this.vulnerable = false;
//         this.currentDirection = Direction.LEFT;
//         updatePosition();
//         setVulnerable(false);
//     }
    
//     // Getters
//     public Group getSprite() {
//         return sprite;
//     }
    
//     public int getGridX() {
//         return gridX;
//     }
    
//     public int getGridY() {
//         return gridY;
//     }
    
//     public boolean isVulnerable() {
//         return vulnerable;
//     }
// }