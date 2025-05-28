# BombSweeper Game (Java + OOP + Swing GUI)

## Overview

BombSweeper is a classic minefield puzzle game implemented in Java using Object-Oriented Programming (OOP) principles and Swing for the graphical user interface. The game features a grid of clickable squares, some containing bombs. The player’s goal is to reveal all non-bomb squares without detonating any bombs.

This project demonstrates key OOP concepts such as encapsulation, inheritance, and polymorphism, alongside event-driven GUI programming.

---

## Features

- **Random Bomb Placement**: Bombs are placed randomly on the board with a configurable probability.
- **Bomb Proximity Counting**: Each square shows the number of adjacent bombs when revealed.
- **Recursive Reveal**: Empty squares with no adjacent bombs recursively reveal neighboring squares.
- **Interactive GUI**: Uses Java Swing to display the game board and handle user interactions.
- **Modular Design**: Clean separation of concerns with `GameBoard`, `GameSquare`, and `BombSquare` classes.

---

## Technologies Used

- Java (JDK 8+)
- Swing (GUI components)
- AWT (Graphics)

---

## How to Run

1. **Compile all Java files:**

   ```bash
   javac *.java
````

2. **Run the Driver class to start the game:**

   ```bash
   java Driver
   ```

---

## File Structure

```
.
├── BombSquare.java      # Logic for bomb squares and reveal behavior
├── GameBoard.java       # Creates and manages the game board GUI and state
├── GameSquare.java      # Abstract base class for all board squares
├── Driver.java          # Entry point; launches the game
├── README.md            # Project documentation
```

---

## Key Classes

* **BombSquare**: Extends `GameSquare`, represents a square that may contain a bomb, handles clicks and recursive revealing.
* **GameSquare**: Abstract class representing any square on the board.
* **GameBoard**: Manages the grid of squares, GUI layout, and user input.
* **Driver**: Contains `main()` method to launch the game window.

---

## Author

**colmantee**
[GitHub Profile](https://github.com/ColdTea15)

---

## License

This project is licensed under the MIT License.
