import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classic fixed Pac-Man maze layout
 */
public class Maze {
    
    public static final int TILE_SIZE = 24;
    public static final int WIDTH = 28;
    public static final int HEIGHT = 31;
    
    private Group mazeGroup;
    private int level;
    
    // Maze layout: 1=wall, 0=empty, 2=dot, 3=power pellet, 4=slow motion, 5=ghost freeze, 6=coin magnet, 7=speed boost, 8=invincibility, 9=double points
    private int[][] layout = {
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
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
    
    public Maze(int level) {
        this.level = level;
        this.mazeGroup = new Group();
        createMaze();
    }
    
    /**
     * Creates the visual maze
     */
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
                    mazeGroup.getChildren().add(dot);
                } else if (layout[row][col] == 3) {
                    // Power pellet
                    Circle powerPellet = new Circle(6);


                    powerPellet.setFill(Color.YELLOW);
                    powerPellet.setStroke(Color.ORANGE);
                    powerPellet.setStrokeWidth(2);
                    powerPellet.setCenterX(x + TILE_SIZE / 2);
                    powerPellet.setCenterY(y + TILE_SIZE / 2);
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
                    mazeGroup.getChildren().add(star);
                } else if (layout[row][col] == 5) {
                    // Ghost freeze power-up (cyan snowflake)
                    Group snowflake = new Group();
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
     * Checks if position is a wall
     */
    public boolean isWall(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return true;
        }
        return layout[y][x] == 1;
    }
    
    /**
     * Checks if position has a dot
     */
    public boolean hasDot(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 2;
    }
    
    /**
     * Checks if position has a power pellet
     */
    public boolean hasPowerPellet(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 3;
    }
    
    /**
     * Checks if position has a slow motion power-up
     */
    public boolean hasSlowMotionPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 4;
    }
    
    /**
     * Checks if position has a ghost freeze power-up
     */
    public boolean hasGhostFreezePowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 5;
    }
    
    /**
     * Checks if position has a coin magnet power-up
     */
    public boolean hasCoinMagnetPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }


        return layout[y][x] == 6;
    }
    
    /**
     * Checks if position has a speed boost power-up
     */
    public boolean hasSpeedBoostPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 7;
    }
    
    /**
     * Checks if position has an invincibility power-up
     */
    public boolean hasInvincibilityPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 8;
    }
    
    /**
     * Checks if position has a double points power-up
     */
    public boolean hasDoublePointsPowerUp(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return layout[y][x] == 9;
    }
    
    /**
     * Removes dot from position
     */
    public void removeDot(int x, int y) {
        if (hasDot(x, y)) {
            layout[y][x] = 0;
            removeDotVisual(x, y, 3);
        }
    }
    
    /**
     * Removes power pellet from position
     */
    public void removePowerPellet(int x, int y) {
        if (hasPowerPellet(x, y)) {
            layout[y][x] = 0;
            removeDotVisual(x, y, 6);
        }
    }
    
    /**
     * Removes slow motion power-up from position
     */
    public void removeSlowMotionPowerUp(int x, int y) {
        if (hasSlowMotionPowerUp(x, y)) {
            layout[y][x] = 0;
            removeSlowMotionVisual(x, y);
        }
    }
    
    /**
     * Removes ghost freeze power-up from position
     */
    public void removeGhostFreezePowerUp(int x, int y) {
        if (hasGhostFreezePowerUp(x, y)) {
            layout[y][x] = 0;
            removeGhostFreezeVisual(x, y);
        }
    }
    
    /**
     * Removes coin magnet power-up from position
     */
    public void removeCoinMagnetPowerUp(int x, int y) {
        if (hasCoinMagnetPowerUp(x, y)) {
            layout[y][x] = 0;
            removeCoinMagnetVisual(x, y);
        }
    }
    
    /**
     * Removes speed boost power-up from position
     */
    public void removeSpeedBoostPowerUp(int x, int y) {
        if (hasSpeedBoostPowerUp(x, y)) {
            layout[y][x] = 0;
            removeSpeedBoostVisual(x, y);
        }
    }
    
    /**
     * Removes invincibility power-up from position
     */
    public void removeInvincibilityPowerUp(int x, int y) {
        if (hasInvincibilityPowerUp(x, y)) {
            layout[y][x] = 0;
            removeInvincibilityVisual(x, y);
        }
    }
    
    /**
     * Removes double points power-up from position
     */
    public void removeDoublePointsPowerUp(int x, int y) {
        if (hasDoublePointsPowerUp(x, y)) {
            layout[y][x] = 0;
            removeDoublePointsVisual(x, y);
        }
    }
    
    /**
     * Removes dot/pellet from visual display
     */
    private void removeDotVisual(int x, int y, double radius) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                return Math.abs(circle.getCenterX() - centerX) < 1 &&
                       Math.abs(circle.getCenterY() - centerY) < 1 &&
                       Math.abs(circle.getRadius() - radius) < 1;
            }
            return false;
        });
    }
    
    /**
     * Removes slow motion power-up from visual display
     */
    private void removeSlowMotionVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof javafx.scene.shape.Polygon) {
                javafx.scene.shape.Polygon polygon = (javafx.scene.shape.Polygon) node;
                // Check if polygon is near this position (star shape)
                if (!polygon.getPoints().isEmpty()) {


                    double firstX = polygon.getPoints().get(0);
                    double firstY = polygon.getPoints().get(1);
                    return Math.abs(firstX - centerX) < 8 && Math.abs(firstY - centerY) < 8;
                }
            }
            return false;
        });
    }
    
    /**
     * Removes ghost freeze power-up from visual display
     */
    private void removeGhostFreezeVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Group) {
                Group group = (Group) node;
                // Check if this is a snowflake group near this position
                if (!group.getChildren().isEmpty() && group.getChildren().get(0) instanceof javafx.scene.shape.Line) {
                    javafx.scene.shape.Line firstLine = (javafx.scene.shape.Line) group.getChildren().get(0);
                    return Math.abs(firstLine.getStartX() - centerX) < 2 && 
                           Math.abs(firstLine.getStartY() - centerY) < 2;
                }
            }
            return false;
        });
    }
    
    /**
     * Removes coin magnet power-up from visual display
     */
    private void removeCoinMagnetVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Group) {
                Group group = (Group) node;
                // Check if this is a magnet group near this position
                if (!group.getChildren().isEmpty() && group.getChildren().get(0) instanceof javafx.scene.shape.Arc) {
                    javafx.scene.shape.Arc firstArc = (javafx.scene.shape.Arc) group.getChildren().get(0);
                    return Math.abs(firstArc.getCenterX() - centerX) < 2 && 
                           Math.abs(firstArc.getCenterY() - centerY) < 2 &&
                           firstArc.getStroke() == Color.GOLD;
                }
            }
            return false;
        });
    }
    
    /**
     * Removes speed boost power-up from visual display
     */
    private void removeSpeedBoostVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Group) {
                Group group = (Group) node;
                if (!group.getChildren().isEmpty() && group.getChildren().get(0) instanceof javafx.scene.shape.Polygon) {
                    javafx.scene.shape.Polygon polygon = (javafx.scene.shape.Polygon) group.getChildren().get(0);
                    if (polygon.getFill() == Color.LIME && !polygon.getPoints().isEmpty()) {
                        double firstX = polygon.getPoints().get(0);
                        double firstY = polygon.getPoints().get(1);
                        return Math.abs(firstX - centerX) < 5 && Math.abs(firstY - centerY) < 10;
                    }
                }
            }
            return false;
        });
    }
    
    /**
     * Removes invincibility power-up from visual display
     */
    private void removeInvincibilityVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Group) {
                Group group = (Group) node;
                if (!group.getChildren().isEmpty() && group.getChildren().get(0) instanceof javafx.scene.shape.Polygon) {
                    javafx.scene.shape.Polygon polygon = (javafx.scene.shape.Polygon) group.getChildren().get(0);
                    if (polygon.getFill() == Color.LIGHTBLUE && !polygon.getPoints().isEmpty()) {
                        double firstX = polygon.getPoints().get(0);


                        double firstY = polygon.getPoints().get(1);
                        return Math.abs(firstX - centerX) < 2 && Math.abs(firstY - centerY) < 10;
                    }
                }
            }
            return false;
        });
    }
    
    /**
     * Removes double points power-up from visual display
     */
    private void removeDoublePointsVisual(int x, int y) {
        double centerX = x * TILE_SIZE + TILE_SIZE / 2;
        double centerY = y * TILE_SIZE + TILE_SIZE / 2;
        
        mazeGroup.getChildren().removeIf(node -> {
            if (node instanceof Group) {
                Group group = (Group) node;
                if (group.getChildren().size() >= 2) {
                    javafx.scene.Node firstChild = group.getChildren().get(0);
                    javafx.scene.Node secondChild = group.getChildren().get(1);
                    
                    if (firstChild instanceof javafx.scene.shape.Polygon && secondChild instanceof Text) {
                        Text text = (Text) secondChild;
                        if (text.getText().equals("2X")) {
                            return Math.abs(text.getX() - (centerX - 6)) < 2 && 
                                   Math.abs(text.getY() - (centerY + 3)) < 2;
                        }
                    }
                }
            }
            return false;
        });
    }
    
    /**
     * Attracts dots toward a position (coin magnet effect)
     * Returns number of dots collected
     */
    public int attractDotsToPosition(int targetX, int targetY, double radius) {
        int dotsCollected = 0;
        double targetPixelX = targetX * TILE_SIZE + TILE_SIZE / 2;
        double targetPixelY = targetY * TILE_SIZE + TILE_SIZE / 2;
        
        // Check all dots in the maze
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (layout[row][col] == 2) { // Only regular dots
                    double distance = Math.sqrt(Math.pow(col - targetX, 2) + Math.pow(row - targetY, 2));
                    
                    // If within magnet radius, collect immediately
                    if (distance <= radius && distance > 0) {
                        layout[row][col] = 0;
                        removeDotVisual(col, row, 3);
                        dotsCollected++;
                    }
                }
            }
        }
        return dotsCollected;
    }
    
    /**
     * Checks if all dots are collected (slow motion, freeze, and magnet power-ups don't count)
     */
    public boolean allDotsCollected() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (layout[row][col] == 2 || layout[row][col] == 3) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Getters
    public Group getMazeGroup() {
        return mazeGroup;
    }
    
    public int getPacmanStartX() {
        return 14;
    }
    
    public int getPacmanStartY() {
        return 23;
    }
}
