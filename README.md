# Pacman Game

This is a Pacman game project developed in Java. The project includes the game logic, graphical assets, and resources needed to run the game.

## Project Structure

- **resources**:
  - **sprites**: Contains all graphical files for the game, such as ghost characters, Pacman, and background images.
  - **level**: Contains a CSV file describing the structure of the game levels.
  
- **java**:
  - **pacman**: Contains the main game launcher file `GameLauncher.java` and subdirectories with the source code:
    - **ghosts**: Files related to the behavior of the ghosts in the game.
    - **game**: General game logic.
    - **helpers**: Various helper classes.
    - **entities**: Classes describing different entities in the game.

## Getting Started

### Prerequisites

1. Install JDK (version 8 or higher).
2. Install an IDE (such as IntelliJ IDEA or Eclipse).
3. Install Git (optional for version control).

### Installation and Running

1. Unzip the project file to a directory on your computer.
2. Open the IDE and select "Open" or "Import Project".
3. Select the `src` directory as the project to open.
4. After opening the project, locate the file `GameLauncher.java` in the `pacman` directory.
5. Right-click on the `GameLauncher.java` file and select "Run" to start the game.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Credits

All rights reserved to Bar Mor.
