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
    
    /*
    Maze layout: 
        1=wall, 
        0=empty, 
        2=dot, 
        3=power pellet, 
        4=slow motion, 
        5=ghost freeze, 
        6=coin magnet, 
        7=speed boost, 
        8=invincibility, 
        9=double points
    */
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
     * 
     */
    private int[][] getClassicMaze() {
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,7,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,3,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,2,2,2,2,7,2,2,2,2,2,2,2,4,4,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
            {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,5,2,2,2,1},
            {1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,0,0,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1},
            {0,0,0,0,0,0,6,0,0,0,1,0,0,0,0,0,0,1,0,0,0,2,0,0,0,0,0,0},
            {1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
            {1,3,2,2,1,1,2,2,2,2,2,2,2,2,5,2,2,2,2,2,2,2,1,1,2,2,3,1},
            {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
            {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
            {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,2,8,2,2,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,9,2,2,2,2,2,2,2,8,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    
    /**
     * LEVEL 2 - Spiral Maze (Medium - Tighter corridors)
     */
    private int[][] getSpiralMaze() {
        // return new int[][] {
        //     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        //     {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        //     {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
        //     {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},
        //     {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1},
        //     {1,2,1,2,1,2,2,2,2,2,2,2,2,4,4,2,2,2,2,2,2,2,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,2,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,2,1,5,5,1,2,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,2,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
        //     {1,2,1,2,1,2,2,2,2,2,2,2,2,7,7,2,2,2,2,2,2,2,2,1,2,1,2,1},
        //     {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1},
        //     {1,2,1,2,2,2,2,2,2,2,2,2,2,8,8,2,2,2,2,2,2,2,2,2,2,1,2,1},
        //     {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
        //     {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
        //     {1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
        //     {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        //     {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
        //     {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        //     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        // };
        return new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,2,2,2,2,4,4,2,2,2,2,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,1,1,1,1,2,2,1,1,1,1,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,5,5,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,3,2,2,2,2,3,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,1,2,1,1,2,1,1,1,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
            {1,2,1,2,1,2,2,2,2,2,2,2,2,7,7,2,2,2,2,2,2,2,2,1,2,1,2,1},
            {1,2,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,8,8,2,2,2,2,2,2,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,6,9,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,3,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
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

    
    
    private void createMaze() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double x = col * TILE_SIZE;
                double y = row * TILE_SIZE;
                
                if (layout[row][col] == 1) {
                    // Wall
                    Rectangle wall = new Rectangle(TILE_SIZE, TILE_SIZE);
                    wall.setFill(Color.BLUE);
                    wall.setStroke(Color.DARKBLUE);
                    wall.setStrokeWidth(1);
                    wall.setX(x);
                    wall.setY(y);
                    mazeGroup.getChildren().add(wall);
                } else if (layout[row][col] == 2) {
                    // Dot
                    Circle dot = new Circle(3);
                    dot.setFill(Color.YELLOW);
                    dot.setCenterX(x + TILE_SIZE / 2);
                    dot.setCenterY(y + TILE_SIZE / 2);
                    dot.setId("dot_" + col + "_" + row); // Add ID for removal
                    mazeGroup.getChildren().add(dot);
                } else if (layout[row][col] == 3) {
                    // Power pellet
                    Circle powerPellet = new Circle(6);
                    powerPellet.setFill(Color.YELLOW);
                    powerPellet.setStroke(Color.ORANGE);
                    powerPellet.setStrokeWidth(2);
                    powerPellet.setCenterX(x + TILE_SIZE / 2);
                    powerPellet.setCenterY(y + TILE_SIZE / 2);
                    powerPellet.setId("pellet_" + col + "_" + row); // Add ID for removal
                    mazeGroup.getChildren().add(powerPellet);
                } else if (layout[row][col] == 4) {
                    // Slow motion power-up (purple star)
                    javafx.scene.shape.Polygon star = new javafx.scene.shape.Polygon();
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    double outerRadius = 7;
                    double innerRadius = 3;
                    
                    // Create 5-pointed star
                    for (int i = 0; i < 10; i++) {
                        double angle = Math.PI / 2 + (i * Math.PI / 5);
                        double radius = (i % 2 == 0) ? outerRadius : innerRadius;
                        star.getPoints().addAll(
                            centerX + radius * Math.cos(angle),
                            centerY - radius * Math.sin(angle)
                        );
                    }
                    
                    star.setFill(Color.PURPLE);
                    star.setStroke(Color.MAGENTA);
                    star.setStrokeWidth(2);
                    star.setId("slowmotion_" + col + "_" + row); // Add ID for removal
                    mazeGroup.getChildren().add(star);
                } else if (layout[row][col] == 5) {
                    // Ghost freeze power-up (cyan snowflake)
                    Group snowflake = new Group();
                    snowflake.setId("ghostfreeze_" + col + "_" + row); // Add ID for removal
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    
                    // Create 6 lines radiating from center (snowflake pattern)
                    for (int i = 0; i < 6; i++) {
                        double angle = i * Math.PI / 3;
                        javafx.scene.shape.Line line = new javafx.scene.shape.Line(
                            centerX, centerY,
                            centerX + 7 * Math.cos(angle), centerY + 7 * Math.sin(angle)
                        );
                        line.setStroke(Color.CYAN);
                        line.setStrokeWidth(2);
                        snowflake.getChildren().add(line);
                        
                        // Add small perpendicular lines at the end
                        double endX = centerX + 7 * Math.cos(angle);
                        double endY = centerY + 7 * Math.sin(angle);
                        double perpAngle1 = angle + Math.PI / 4;
                        double perpAngle2 = angle - Math.PI / 4;
                        
                        javafx.scene.shape.Line perp1 = new javafx.scene.shape.Line(
                            endX, endY,
                            endX + 3 * Math.cos(perpAngle1), endY + 3 * Math.sin(perpAngle1)
                        
                        );
                        perp1.setStroke(Color.CYAN);
                        perp1.setStrokeWidth(1.5);
                        snowflake.getChildren().add(perp1);
                        
                        javafx.scene.shape.Line perp2 = new javafx.scene.shape.Line(
                            endX, endY,
                            endX + 3 * Math.cos(perpAngle2), endY + 3 * Math.sin(perpAngle2)
                        );
                        perp2.setStroke(Color.CYAN);
                        perp2.setStrokeWidth(1.5);
                        snowflake.getChildren().add(perp2);
                    }
                    
                    // Add center circle
                    Circle center = new Circle(centerX, centerY, 2);
                    center.setFill(Color.CYAN);
                    center.setStroke(Color.LIGHTBLUE);
                    center.setStrokeWidth(1);
                    snowflake.getChildren().add(center);
                    
                    mazeGroup.getChildren().add(snowflake);
                } else if (layout[row][col] == 6) {
                    // Coin magnet power-up (golden horseshoe magnet)
                    Group magnet = new Group();
                    magnet.setId("coinmagnet_" + col + "_" + row); // Add ID for removal
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    
                    // Create horseshoe shape (U-shape)
                    javafx.scene.shape.Arc horseshoe = new javafx.scene.shape.Arc();
                    horseshoe.setCenterX(centerX);
                    horseshoe.setCenterY(centerY);
                    horseshoe.setRadiusX(6);
                    horseshoe.setRadiusY(6);
                    horseshoe.setStartAngle(180);
                    horseshoe.setLength(180);
                    horseshoe.setType(javafx.scene.shape.ArcType.OPEN);
                    horseshoe.setFill(null);
                    horseshoe.setStroke(Color.GOLD);
                    horseshoe.setStrokeWidth(3);
                    magnet.getChildren().add(horseshoe);
                    
                    // Add magnetic field lines (small arcs around it)
                    for (int i = 0; i < 3; i++) {
                        javafx.scene.shape.Arc fieldLine = new javafx.scene.shape.Arc();
                        fieldLine.setCenterX(centerX);
                        fieldLine.setCenterY(centerY);
                        fieldLine.setRadiusX(8 + i * 2);
                        fieldLine.setRadiusY(8 + i * 2);
                        fieldLine.setStartAngle(200 + i * 5);
                        fieldLine.setLength(140 - i * 10);
                        fieldLine.setType(javafx.scene.shape.ArcType.OPEN);
                        fieldLine.setFill(null);
                        fieldLine.setStroke(Color.ORANGE);
                        fieldLine.setStrokeWidth(1);
                        fieldLine.setOpacity(0.6 - i * 0.15);
                        magnet.getChildren().add(fieldLine);
                    }
                    
                    mazeGroup.getChildren().add(magnet);
                } else if (layout[row][col] == 7) {
                    // Speed boost power-up (green lightning bolt)
                    Group lightning = new Group();
                    lightning.setId("speedboost_" + col + "_" + row); // Add ID for removal
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    
                    // Create lightning bolt shape
                    javafx.scene.shape.Polygon bolt = new javafx.scene.shape.Polygon();
                    bolt.getPoints().addAll(
                        centerX, centerY - 8,
                        centerX + 3, centerY - 2,
                        centerX + 1, centerY,
                        centerX + 4, centerY + 2,
                        centerX, centerY + 8,
                        centerX - 3, centerY + 2,
                        centerX - 1, centerY,
                        centerX - 4, centerY - 2
                    );
                    bolt.setFill(Color.LIME);
                    
                    bolt.setStroke(Color.GREEN);
                    bolt.setStrokeWidth(2);
                    lightning.getChildren().add(bolt);
                    
                    mazeGroup.getChildren().add(lightning);
                } else if (layout[row][col] == 8) {
                    // Invincibility power-up (light blue shield)
                    Group shield = new Group();
                    shield.setId("invincibility_" + col + "_" + row); // Add ID for removal
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    
                    // Create shield shape
                    javafx.scene.shape.Polygon shieldShape = new javafx.scene.shape.Polygon();
                    shieldShape.getPoints().addAll(
                        centerX, centerY - 8,
                        centerX + 6, centerY - 4,
                        centerX + 6, centerY + 4,
                        centerX, centerY + 8,
                        centerX - 6, centerY + 4,
                        centerX - 6, centerY - 4
                    );
                    shieldShape.setFill(Color.LIGHTBLUE);
                    shieldShape.setStroke(Color.DEEPSKYBLUE);

                    shieldShape.setStrokeWidth(2);
                    shield.getChildren().add(shieldShape);
                    
                    // Add cross in center
                    javafx.scene.shape.Line line1 = new javafx.scene.shape.Line(
                        centerX - 3, centerY, centerX + 3, centerY
                    );
                    line1.setStroke(Color.WHITE);
                    line1.setStrokeWidth(2);
                    shield.getChildren().add(line1);
                    
                    javafx.scene.shape.Line line2 = new javafx.scene.shape.Line(
                        centerX, centerY - 3, centerX, centerY + 3
                    );
                    line2.setStroke(Color.WHITE);
                    line2.setStrokeWidth(2);
                    shield.getChildren().add(line2);
                    
                    mazeGroup.getChildren().add(shield);
                } else if (layout[row][col] == 9) {
                    // Double points power-up (yellow "2X" text)
                    Group doublePoints = new Group();
                    doublePoints.setId("doublepoints_" + col + "_" + row); // Add ID for removal
                    double centerX = x + TILE_SIZE / 2;
                    double centerY = y + TILE_SIZE / 2;
                    
                    // Create star background
                    javafx.scene.shape.Polygon star = new javafx.scene.shape.Polygon();
                    double outerRadius = 8;
                    double innerRadius = 4;
                    
                    for (int i = 0; i < 8; i++) {
                        double angle = (i * Math.PI / 4);
                        double radius = (i % 2 == 0) ? outerRadius : innerRadius;
                        star.getPoints().addAll(
                            centerX + radius * Math.cos(angle),
                            centerY + radius * Math.sin(angle)
                        );
                    }
                    
                    star.setFill(Color.YELLOW);
                    star.setStroke(Color.ORANGE);
                    star.setStrokeWidth(1.5);
                    doublePoints.getChildren().add(star);
                    
                    // Add "2X" text
                    Text text = new Text("2X");
                    text.setFill(Color.DARKRED);
                    text.setFont(Font.font("Arial", FontWeight.BOLD, 8));
                    text.setX(centerX - 6);
                    text.setY(centerY + 3);
                    doublePoints.getChildren().add(text);
                    
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
