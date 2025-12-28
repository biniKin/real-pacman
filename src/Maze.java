import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Pac-Man maze with multiple layouts for different levels
 */
public class Maze {
    
    public static final int TILE_SIZE = 24;
    public static final int WIDTH = 28;
    public static final int HEIGHT = 31;
    
    private Group mazeGroup;
    private int level;
    
    // Maze layout: 1=wall, 0=empty, 2=dot, 3=power pellet, 4=slow motion, 5=ghost freeze, 6=coin magnet, 7=speed boost, 8=invincibility, 9=double points
    private int[][] layout;
    
    public Maze(int level) {
        this.level = level;
        this.mazeGroup = new Group();
        this.layout = getMazeLayout(level);
        createMaze();
    }
    
    /**
     * Returns the appropriate maze layout based on level
     */
    private int[][] getMazeLayout(int level) {
        // Cycle through 4 different maze layouts
        int mazeIndex = (level - 1) % 4;
        
        switch (mazeIndex) {
            case 0: return getClassicMaze();
            case 1: return getSpiralMaze();
            case 2: return getCrossMaze();
            case 3: return getDiamondMaze();
            default: return getClassicMaze();
        }
    }

    
    /**
     * LEVEL 1 - Classic Maze (Easy)
     */
    private int[][] getClassicMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,3,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,4,4,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
            {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,0,0,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1},
            {0,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,0,1,0,0,0,2,0,0,0,0,0,0},
            {1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,3,2,2,1,1,2,2,2,2,2,2,2,5,5,2,2,2,2,2,2,2,1,1,2,2,3,1},
            {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
            {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
            {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,7,7,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,8,8,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    
    /**
     * LEVEL 2 - Spiral Maze (Medium - Tighter corridors)
     */
    private int[][] getSpiralMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,2,2,2,2,4,4,2,2,2,2,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,5,5,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,2,2,2,2,7,7,2,2,2,2,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,8,8,2,2,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    
    /**
     * LEVEL 3 - Cross Maze (Hard - Complex intersections)
     */
    private int[][] getCrossMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,3,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,3,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,4,4,2,2,1,2,1,2,1,1,2,1,2,1,2,2,7,7,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,5,5,2,2,2,2,2,2,1,0,1,1,0,1,2,2,2,2,2,8,8,1,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,6,9,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,3,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,3,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }
    
    /**
     * LEVEL 4+ - Diamond Maze (Very Hard - Narrow passages)
     */
    private int[][] getDiamondMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,4,4,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,3,1,5,5,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,7,7,1,3,1,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,3,1,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,8,8,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    
    /**
     * LEVEL 3 - Cross Maze (Hard - More complex paths)
     */
    private int[][] getCrossMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,3,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,3,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,4,4,2,2,1,2,1,2,1,1,2,1,2,1,2,2,7,7,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,5,5,2,2,2,2,2,2,1,0,1,1,0,1,2,2,2,2,2,8,8,1,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,6,9,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,3,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,3,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }
    
    /**
     * LEVEL 4+ - Diamond Maze (Very Hard - Most challenging)
     */
    private int[][] getDiamondMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,4,4,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,3,1,5,5,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,7,7,1,3,1,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,3,1,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,8,8,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,1,2,1,2,1,1,2,1,2,1,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }
    
    /**
     * Creates the visual maze with walls, dots, and power-ups
     */
    private void createMaze() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int cell = layout[y][x];
                
                if (cell == 1) {
                    // Wall
                    Rectangle wall = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    wall.setFill(Color.BLUE);
                    wall.setStroke(Color.DARKBLUE);
                    wall.setStrokeWidth(1);
                    mazeGroup.getChildren().add(wall);
                } else if (cell == 2) {
                    // Dot
                    Circle dot = new Circle(x * TILE_SIZE + TILE_SIZE / 2, y * TILE_SIZE + TILE_SIZE / 2, 3);
                    dot.setFill(Color.WHITE);
                    dot.setId("dot_" + x + "_" + y);
                    mazeGroup.getChildren().add(dot);
                } else if (cell == 3) {
                    // Power Pellet
                    Circle pellet = new Circle(x * TILE_SIZE + TILE_SIZE / 2, y * TILE_SIZE + TILE_SIZE / 2, 6);
                    pellet.setFill(Color.WHITE);
                    pellet.setId("pellet_" + x + "_" + y);
                    mazeGroup.getChildren().add(pellet);
                } else if (cell == 4) {
                    // Slow Motion Power-Up (purple star)
                    Text star = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 8, y * TILE_SIZE + TILE_SIZE / 2 + 6, "★");
                    star.setFill(Color.PURPLE);
                    star.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                    star.setId("slowmotion_" + x + "_" + y);
                    mazeGroup.getChildren().add(star);
                } else if (cell == 5) {
                    // Ghost Freeze Power-Up (cyan snowflake)
                    Text snowflake = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 8, y * TILE_SIZE + TILE_SIZE / 2 + 6, "❄");
                    snowflake.setFill(Color.CYAN);
                    snowflake.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                    snowflake.setId("ghostfreeze_" + x + "_" + y);
                    mazeGroup.getChildren().add(snowflake);
                } else if (cell == 6) {
                    // Coin Magnet Power-Up (gold horseshoe magnet)
                    Text magnet = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 8, y * TILE_SIZE + TILE_SIZE / 2 + 6, "⊃");
                    magnet.setFill(Color.GOLD);
                    magnet.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                    magnet.setId("coinmagnet_" + x + "_" + y);
                    mazeGroup.getChildren().add(magnet);
                } else if (cell == 7) {
                    // Speed Boost Power-Up (green lightning bolt)
                    Text lightning = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 8, y * TILE_SIZE + TILE_SIZE / 2 + 6, "⚡");
                    lightning.setFill(Color.LIME);
                    lightning.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                    lightning.setId("speedboost_" + x + "_" + y);
                    mazeGroup.getChildren().add(lightning);
                } else if (cell == 8) {
                    // Invincibility Power-Up (light blue shield)
                    Text shield = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 8, y * TILE_SIZE + TILE_SIZE / 2 + 6, "🛡");
                    shield.setFill(Color.LIGHTBLUE);
                    shield.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    shield.setId("invincibility_" + x + "_" + y);
                    mazeGroup.getChildren().add(shield);
                } else if (cell == 9) {
                    // Double Points Power-Up (yellow star with "2X")
                    Text doublePoints = new Text(x * TILE_SIZE + TILE_SIZE / 2 - 10, y * TILE_SIZE + TILE_SIZE / 2 + 5, "2X");
                    doublePoints.setFill(Color.YELLOW);
                    doublePoints.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    doublePoints.setId("doublepoints_" + x + "_" + y);
                    mazeGroup.getChildren().add(doublePoints);
                }
            }
        }
    }
    
    /**
     * Checks if a position is a wall
     */
    public boolean isWall(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return true;
        }
        return layout[y][x] == 1;
    }
    
    /**
     * Checks if a position has a dot
     */
    public boolean hasDot(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 2;
    }
    
    /**
     * Checks if a position has a power pellet
     */
    public boolean hasPowerPellet(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 3;
    }
    
    /**
     * Checks if a position has a slow motion power-up
     */
    public boolean hasSlowMotionPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 4;
    }
    
    /**
     * Checks if a position has a ghost freeze power-up
     */
    public boolean hasGhostFreezePowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 5;
    }
    
    /**
     * Checks if a position has a coin magnet power-up
     */
    public boolean hasCoinMagnetPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 6;
    }
    
    /**
     * Checks if a position has a speed boost power-up
     */
    public boolean hasSpeedBoostPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 7;
    }
    
    /**
     * Checks if a position has an invincibility power-up
     */
    public boolean hasInvincibilityPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 8;
    }
    
    /**
     * Checks if a position has a double points power-up
     */
    public boolean hasDoublePointsPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 9;
    }
    
    /**
     * Removes a dot from the maze
     */
    public void removeDot(int x, int y) {
        if (hasDot(x, y)) {
            layout[y][x] = 0;
            removeDotVisual(x, y);
        }
    }
    
    /**
     * Removes a power pellet from the maze
     */
    public void removePowerPellet(int x, int y) {
        if (hasPowerPellet(x, y)) {
            layout[y][x] = 0;
            mazeGroup.getChildren().removeIf(node -> 
                node.getId() != null && node.getId().equals("pellet_" + x + "_" + y));
        }
    }
    
    /**
     * Removes a slow motion power-up from the maze
     */
    public void removeSlowMotionPowerUp(int x, int y) {
        if (hasSlowMotionPowerUp(x, y)) {
            layout[y][x] = 0;
            removeSlowMotionVisual(x, y);
        }
    }
    
    /**
     * Removes a ghost freeze power-up from the maze
     */
    public void removeGhostFreezePowerUp(int x, int y) {
        if (hasGhostFreezePowerUp(x, y)) {
            layout[y][x] = 0;
            removeGhostFreezeVisual(x, y);
        }
    }
    
    /**
     * Removes a coin magnet power-up from the maze
     */
    public void removeCoinMagnetPowerUp(int x, int y) {
        if (hasCoinMagnetPowerUp(x, y)) {
            layout[y][x] = 0;
            removeCoinMagnetVisual(x, y);
        }
    }
    
    /**
     * Removes a speed boost power-up from the maze
     */
    public void removeSpeedBoostPowerUp(int x, int y) {
        if (hasSpeedBoostPowerUp(x, y)) {
            layout[y][x] = 0;
            removeSpeedBoostVisual(x, y);
        }
    }
    
    /**
     * Removes an invincibility power-up from the maze
     */
    public void removeInvincibilityPowerUp(int x, int y) {
        if (hasInvincibilityPowerUp(x, y)) {
            layout[y][x] = 0;
            removeInvincibilityVisual(x, y);
        }
    }
    
    /**
     * Removes a double points power-up from the maze
     */
    public void removeDoublePointsPowerUp(int x, int y) {
        if (hasDoublePointsPowerUp(x, y)) {
            layout[y][x] = 0;
            removeDoublePointsVisual(x, y);
        }
    }
    
    /**
     * Removes dot visual from the maze group
     */
    private void removeDotVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("dot_" + x + "_" + y));
    }
    
    /**
     * Removes slow motion visual from the maze group
     */
    private void removeSlowMotionVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("slowmotion_" + x + "_" + y));
    }
    
    /**
     * Removes ghost freeze visual from the maze group
     */
    private void removeGhostFreezeVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("ghostfreeze_" + x + "_" + y));
    }
    
    /**
     * Removes coin magnet visual from the maze group
     */
    private void removeCoinMagnetVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("coinmagnet_" + x + "_" + y));
    }
    
    /**
     * Removes speed boost visual from the maze group
     */
    private void removeSpeedBoostVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("speedboost_" + x + "_" + y));
    }
    
    /**
     * Removes invincibility visual from the maze group
     */
    private void removeInvincibilityVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("invincibility_" + x + "_" + y));
    }
    
    /**
     * Removes double points visual from the maze group
     */
    private void removeDoublePointsVisual(int x, int y) {
        mazeGroup.getChildren().removeIf(node -> 
            node.getId() != null && node.getId().equals("doublepoints_" + x + "_" + y));
    }
    
    /**
     * Attracts dots within radius to target position (coin magnet effect)
     * Returns the number of dots collected
     */
    public int attractDotsToPosition(int targetX, int targetY, double radius) {
        int dotsCollected = 0;
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (layout[y][x] == 2) {
                    // Calculate distance
                    double distance = Math.sqrt(Math.pow(x - targetX, 2) + Math.pow(y - targetY, 2));
                    
                    if (distance <= radius && distance > 0) {
                        // Remove the dot
                        layout[y][x] = 0;
                        removeDotVisual(x, y);
                        dotsCollected++;
                    }
                }
            }
        }
        
        return dotsCollected;
    }
    
    /**
     * Checks if all dots have been collected
     */
    public boolean allDotsCollected() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (layout[y][x] == 2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Returns the maze group for rendering
     */
    public Group getMazeGroup() {
        return mazeGroup;
    }
    
    /**
     * Returns Pacman's starting X position
     */
    public int getPacmanStartX() {
        return 14;
    }
    
    /**
     * Returns Pacman's starting Y position
     */
    public int getPacmanStartY() {
        return 23;
    }
}
