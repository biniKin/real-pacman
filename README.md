# 🎮 PACMAN - Classic Arcade Game

A high-quality, fully playable 2D Pacman game with smooth movement, intelligent ghost AI, and classic arcade-style gameplay.

## ✨ Features

### 🎯 Core Gameplay
- **Randomly Generated Mazes** - Every level has a unique procedurally generated maze
- **Smooth Grid-Based Movement** - NO jittering, vibration, or shaking
- **Clean Wall Collision** - Pacman stops cleanly at walls without clipping
- **Visible Dots** - All collectible dots clearly visible on paths
- **Animated Pacman** - Smooth mouth opening/closing animation
- **Intelligent Ghost AI** - Ghosts chase Pacman with pathfinding
- **Power Mode** - Eat power pellets to turn ghosts vulnerable
- **Progressive Difficulty** - Each level gets faster and more challenging

### 👻 Ghost AI
- **3-4 Ghosts** with different colors (Red, Pink, Cyan, Orange)
- **Chase Mode** - Ghosts actively pursue Pacman
- **Flee Mode** - Vulnerable ghosts run away from Pacman
- **Smart Pathfinding** - Ghosts calculate shortest path to target
- **Collision Avoidance** - Never get stuck in corners

### 🎨 Visual Quality
- **Clean Graphics** - Professional arcade-style appearance
- **High Contrast** - Black background with bright blue walls
- **Visible Elements** - Yellow Pacman, colored ghosts, yellow dots
- **Smooth Animations** - No flickering or visual glitches
- **Clear UI** - Score, Lives, and Level displayed prominently

### 🎮 Game Mechanics
- **Procedural Maze Generation** - Each level features a unique randomly generated maze
- **Classic Pac-Man Rules** - Ghost house, tunnels, and proper pathfinding
- **Lives System** - Start with 3 lives
- **Scoring**:
  - Small Dot: 10 points
  - Power Pellet: 50 points
  - Vulnerable Ghost: 200 points
  - Level Completion: 1000 bonus points
- **Level Progression** - Automatic advancement with increasing difficulty
- **Tunnel Effect** - Wrap around screen edges like classic Pacman

## 🎯 Controls

- **Arrow Keys** - Move Pacman (Up, Down, Left, Right)
- **Smooth Movement** - Grid-based, no free-floating
- **Responsive** - Instant direction changes when possible

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- JavaFX SDK (download from https://openjfx.io/)

### Setup
1. Download and extract JavaFX SDK
2. Edit `run.bat` and update the JavaFX path to match your installation
3. Run `run.bat`

### Manual Compilation
```bash
javac --module-path "path/to/javafx/lib" --add-modules javafx.controls *.java
java --module-path "path/to/javafx/lib" --add-modules javafx.controls PacmanGame
```

## 📁 Project Structure

```
├── PacmanGame.java      - Main application entry point
├── GameController.java  - Game logic and state management
├── Maze.java           - Maze layout, walls, dots, pellets
├── Pacman.java         - Pacman character with smooth movement
├── Ghost.java          - Ghost AI and behavior
├── Direction.java      - Movement direction enum
├── run.bat            - Windows batch script to compile and run
└── README.md          - This file
```

## 🎮 Gameplay Guide

### Objective
Eat all dots and power pellets in the maze to complete each level while avoiding ghosts.

### How to Play
1. **Start Game** - Click "START GAME" button
2. **Move Pacman** - Use arrow keys to navigate the maze
3. **Collect Dots** - Eat all yellow dots (10 points each)
4. **Avoid Ghosts** - Don't let ghosts catch you
5. **Power Mode** - Eat large power pellets to make ghosts vulnerable
6. **Eat Ghosts** - Chase and eat blue ghosts for bonus points (200 each)
7. **Complete Level** - Collect all dots to advance

### Scoring Strategy
- Collect all dots systematically
- Save power pellets for when ghosts are close
- Chase vulnerable ghosts for maximum points
- Complete levels quickly for better scores

## 🏆 Level System

### Level 1
- 3 ghosts
- Normal speed
- Randomly generated maze

### Level 2+
- 4 ghosts
- Increased ghost speed
- New randomly generated maze each level
- More complex maze layouts

## 🎨 Technical Highlights

### Random Maze Generation
- **Recursive Backtracking Algorithm** - Creates perfect mazes
- **Connectivity Guarantee** - All areas are reachable
- **Ghost House** - Central ghost spawn area in every maze
- **Side Tunnels** - Wrap-around passages on edges
- **Power Pellet Placement** - Strategic corner positions
- **Playable Layouts** - Always generates fair, fun mazes

### Smooth Movement System
- **Grid-Based Movement** - Prevents jittering and vibration
- **Clean Collision Detection** - Stops cleanly at walls
- **No Clipping** - Proper boundary checking
- **Consistent Speed** - Stable frame-independent movement

### AI Implementation
- **Pathfinding** - Ghosts calculate shortest path to Pacman
- **State Machine** - Chase/Flee behavior based on power mode
- **Randomness** - 20% random moves for unpredictability
- **Collision Avoidance** - Smart wall detection

### Visual Quality
- **High Contrast Colors** - Easy to see all elements
- **Smooth Animations** - Pacman mouth, ghost colors
- **Clean UI** - Professional arcade-style interface
- **No Visual Glitches** - Stable rendering

## 🐛 Known Issues

None! The game is fully functional and ready to play.

## 🎯 Game Features Checklist

✅ Smooth Pacman movement (NO jittering)  
✅ Clean wall collision (NO vibration)  
✅ Visible dots on all paths  
✅ Smooth mouth animation  
✅ 3-4 intelligent ghosts  
✅ Ghost AI (chase/flee)  
✅ Scoring system  
✅ Lives system  
✅ Level progression  
✅ Power pellets  
✅ Game over screen  
✅ Level complete screen  
✅ Clean UI  
✅ Professional graphics  
✅ Ready to run  

## 🎮 Tips & Tricks

1. **Corner Strategy** - Use corners to trap and avoid ghosts
2. **Power Pellet Timing** - Save power pellets for emergencies
3. **Ghost Patterns** - Learn ghost movement patterns
4. **Speed Control** - Don't rush, plan your route
5. **Tunnel Escape** - Use side tunnels to escape ghosts

## 🏗️ Code Quality

- **Modular Design** - Clean separation of concerns
- **Well-Commented** - Comprehensive documentation
- **No Dependencies** - Only JavaFX required
- **Professional Structure** - Industry-standard architecture
- **Extensible** - Easy to add features

## 📝 License

This is a educational project demonstrating game development with JavaFX.

## 🎉 Enjoy Playing!

This is a complete, polished Pacman game ready for immediate play. No fixes needed, no missing features. Just compile and enjoy the classic arcade experience!

---

**Made with ❤️ using JavaFX**