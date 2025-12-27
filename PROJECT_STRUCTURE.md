# 📁 Pacman Game - Project Structure

## 🗂️ Directory Organization

```
pacman-ai/
│
├── src/                          # Source code files
│   ├── PacmanGame.java          # Main application entry point
│   ├── GameController.java      # Game logic and state management
│   ├── Maze.java                # Maze layout and dot management
│   ├── Pacman.java              # Pacman character
│   ├── Ghost.java               # Ghost AI and behavior
│   ├── Direction.java           # Movement direction enum
│   └── Game.java                # (Legacy - can be removed)
│
├── bin/                          # Compiled class files
│   ├── PacmanGame.class
│   ├── GameController.class
│   ├── Maze.class
│   ├── Pacman.class
│   ├── Ghost.class
│   ├── Direction.class
│   └── ... (other .class files)
│
├── docs/                         # Documentation
│   ├── README.md                # Main game documentation
│   ├── MAZE_GENERATION.md       # Maze generation info (if applicable)
│   │
│   └── abilities/               # Ability documentation
│       ├── SLOW_MOTION_ABILITY.md
│       ├── GHOST_FREEZE_ABILITY.md
│       └── COIN_MAGNET_ABILITY.md
│
├── run.bat                       # Windows run script
├── QUICKSTART.txt               # Quick start guide
├── GAME_FEATURES.txt            # Feature list
└── PROJECT_STRUCTURE.md         # This file

```

---

## 📄 File Descriptions

### Source Code (`src/`)

#### Core Game Files
- **PacmanGame.java** - Main entry point, initializes the game window
- **GameController.java** - Manages game state, abilities, scoring, and game loop
- **Maze.java** - Handles maze layout, walls, dots, and power-ups
- **Pacman.java** - Pacman character with movement and animation
- **Ghost.java** - Ghost AI with chase/flee behavior
- **Direction.java** - Enum for movement directions (UP, DOWN, LEFT, RIGHT)

### Compiled Files (`bin/`)
- Contains all `.class` files generated during compilation
- Automatically created when running `run.bat`
- Can be safely deleted and regenerated

### Documentation (`docs/`)

#### Main Documentation
- **README.md** - Complete game documentation with features and controls
- **MAZE_GENERATION.md** - Information about maze generation (if applicable)

#### Ability Documentation (`docs/abilities/`)
- **SLOW_MOTION_ABILITY.md** - Slow motion power-up documentation
- **GHOST_FREEZE_ABILITY.md** - Ghost freeze power-up documentation
- **COIN_MAGNET_ABILITY.md** - Coin magnet power-up documentation

### Root Files
- **run.bat** - Compilation and execution script for Windows
- **QUICKSTART.txt** - Quick start guide for new players
- **GAME_FEATURES.txt** - Comprehensive feature checklist
- **PROJECT_STRUCTURE.md** - This file

---

## 🎮 Game Components

### Power-Ups (in order of appearance in maze)
1. **Power Pellets** (Yellow circles) - Row 3 corners
2. **Slow Motion** (Purple stars) - Row 5 center
3. **Ghost Freeze** (Cyan snowflakes) - Row 23 center
4. **Coin Magnet** (Gold horseshoe) - Row 29 center

### Abilities Summary
| Ability | Duration | Effect | Score | Location |
|---------|----------|--------|-------|----------|
| Power Pellet | 8s | Vulnerable ghosts | 50 | Corners |
| Slow Motion | 5s | 50% ghost speed | 100 | Row 5 |
| Ghost Freeze | 4s | Freeze ghosts | 150 | Row 23 |
| Coin Magnet | 7s | Attract coins | 200 | Row 29 |

---

## 🚀 How to Run

### Windows
```bash
run.bat
```

### Manual Compilation
```bash
# Compile
javac --module-path "path\to\javafx\lib" --add-modules javafx.controls -d bin src\*.java

# Run
java --module-path "path\to\javafx\lib" --add-modules javafx.controls -cp bin PacmanGame
```

---

## 🔧 Development

### Adding New Features
1. Add source code to `src/` folder
2. Update `run.bat` if needed
3. Document in `docs/` folder
4. Update this structure file

### Cleaning Build
```bash
# Delete all .class files
del /Q bin\*.class
```

### Project Organization Rules
- ✅ All `.java` files go in `src/`
- ✅ All `.class` files go in `bin/`
- ✅ All `.md` documentation goes in `docs/`
- ✅ Ability docs go in `docs/abilities/`
- ✅ Scripts stay in root directory

---

## 📊 Code Statistics

### Source Files: 7
- PacmanGame.java
- GameController.java
- Maze.java
- Pacman.java
- Ghost.java
- Direction.java
- Game.java (legacy)

### Documentation Files: 7
- README.md
- MAZE_GENERATION.md
- SLOW_MOTION_ABILITY.md
- GHOST_FREEZE_ABILITY.md
- COIN_MAGNET_ABILITY.md
- QUICKSTART.txt
- GAME_FEATURES.txt

### Total Lines of Code: ~2000+

---

## 🎯 Quick Navigation

### Want to...
- **Play the game?** → Run `run.bat`
- **Read documentation?** → Check `docs/README.md`
- **Learn about abilities?** → Check `docs/abilities/`
- **Modify code?** → Edit files in `src/`
- **See features?** → Read `GAME_FEATURES.txt`
- **Quick start?** → Read `QUICKSTART.txt`

---

## 📝 Notes

- The project uses JavaFX for graphics
- All abilities work independently and can stack
- The game is fully functional and ready to play
- No external assets required (all graphics are code-generated)
- Clean, modular architecture for easy maintenance

---

**Last Updated:** December 27, 2025  
**Version:** 1.0  
**Status:** ✅ Production Ready