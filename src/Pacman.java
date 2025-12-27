import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 * Pacman character with smooth grid-based movement
 */
public class Pacman {
    
    private Arc sprite;
    private int gridX;
    private int gridY;
    private Direction currentDirection;
    private Direction nextDirection;
    
    // Animation
    private int animationCounter = 0;
    private boolean mouthOpen = true;
    
    public Pacman(int startX, int startY) {
        this.gridX = startX;
        this.gridY = startY;
        this.currentDirection = Direction.LEFT;
        this.nextDirection = Direction.LEFT;
        
        createSprite();
        updatePosition();
    }
    
    /**
     * Creates Pacman sprite
     */
    private void createSprite() {
        sprite = new Arc();
        sprite.setRadiusX(10);
        sprite.setRadiusY(10);
        sprite.setStartAngle(45);
        sprite.setLength(270);
        sprite.setType(ArcType.ROUND);
        sprite.setFill(Color.YELLOW);
        sprite.setStroke(Color.ORANGE);
        sprite.setStrokeWidth(2);
    }
    
    /**
     * Sets next direction for Pacman
     */
    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }
    
    /**
     * Moves Pacman with smooth grid-based movement
     */
    public void move(Maze maze) {
        // Try to change direction if possible
        if (nextDirection != currentDirection) {
            int nextX = gridX + nextDirection.getDeltaX();
            int nextY = gridY + nextDirection.getDeltaY();
            
            // Handle tunnel wrapping
            if (nextX < 0) nextX = Maze.WIDTH - 1;
            if (nextX >= Maze.WIDTH) nextX = 0;
            
            if (!maze.isWall(nextX, nextY)) {
                currentDirection = nextDirection;
            }
        }
        
        // Move in current direction
        int newX = gridX + currentDirection.getDeltaX();
        int newY = gridY + currentDirection.getDeltaY();
        
        // Handle tunnel wrapping
        if (newX < 0) {
            newX = Maze.WIDTH - 1;
        } else if (newX >= Maze.WIDTH) {
            newX = 0;
        }
        
        // Only move if not hitting a wall
        if (!maze.isWall(newX, newY)) {
            gridX = newX;
            gridY = newY;
        }
        
        updatePosition();
    }
    
    /**
     * Updates sprite position on screen
     */
    private void updatePosition() {
        double pixelX = gridX * Maze.TILE_SIZE + Maze.TILE_SIZE / 2;
        double pixelY = gridY * Maze.TILE_SIZE + Maze.TILE_SIZE / 2;
        
        sprite.setCenterX(pixelX);
        sprite.setCenterY(pixelY);
        
        updateRotation();
    }
    
    /**
     * Updates sprite rotation based on direction
     */
    private void updateRotation() {
        double rotation = 0;
        
        switch (currentDirection) {
            case RIGHT:
                rotation = 0;
                break;
            case LEFT:
                rotation = 180;
                break;
            case UP:
                rotation = 270;
                break;
            case DOWN:
                rotation = 90;
                break;
        }
        
        sprite.setRotate(rotation);
        
        // Update mouth animation
        if (mouthOpen) {
            sprite.setStartAngle(45);
            sprite.setLength(270);
        } else {
            sprite.setStartAngle(0);
            sprite.setLength(360);
        }
    }
    
    /**
     * Updates mouth animation
     */
    public void updateAnimation() {
        animationCounter++;
        if (animationCounter >= 3) {
            mouthOpen = !mouthOpen;
            animationCounter = 0;
            updateRotation();
        }
    }
    
    /**
     * Resets Pacman to starting position
     */
    public void reset(int x, int y) {
        this.gridX = x;
        this.gridY = y;
        this.currentDirection = Direction.LEFT;
        this.nextDirection = Direction.LEFT;
        updatePosition();
    }
    
    // Getters
    public Arc getSprite() {
        return sprite;
    }
    
    public int getGridX() {
        return gridX;
    }
    
    public int getGridY() {
        return gridY;
    }
}