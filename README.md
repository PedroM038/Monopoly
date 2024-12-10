# UFPR Monopoly Game

A simplified Monopoly-style board game implemented in Java, themed around the Federal University of Paraná (UFPR).

## Project Description

This is a digital board game where players can:
- Roll dice and move around the board
- Buy university properties
- Draw cards with random events
- Pay/receive money
- Go to jail/vacation
- Save/load game states

## Setup & Running

### Prerequisites
- Java Runtime Environment (JRE)
- Java Development Kit (JDK)
- Make utility

### Building and Running
```bash
# Build the project
make

# Run the game
make run

# Clean build files
make clean
```
## Project Structure

```
.
├── src/
│   ├── codemodel/      # Game logic and data models
│   │   ├── assets/     # Game assets (cards, spaces, etc.)
│   │   └── ...
│   └── view/           # UI components
├── assets/             # Game data files
└── Makefile           # Build configuration
```

## Authors
Pedro Henrique Marques de Lima (GRR 20235166)
Helen Kaori Iijima (GRR 20235160)
Roberto Sprengel Minozzo Tomchak (GRR 20232369)
...
