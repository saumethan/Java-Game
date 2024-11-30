package game;

import java.util.ArrayList;
import game.challenges.Enemy;
import game.challenges.IChallenge;
import game.challenges.Puzzle;
import game.combat.Weapon;
import game.fileHandling.CustomFileReader;
import game.ui.GameUI;
import game.ui.UIObserver;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class Game implements UIObserver {

    //------------ Variables ----------------- 
    private static Game instance;
    private Player player;
    private Room[][] roomMap; 
    private ArrayList<IChallenge> challenges;
    private ArrayList<String> userCommands;
    private ArrayList<ICommand> commands;
    private int rows;
    private int cols;
    private GameUI gameUI;
    private Boolean gameRunning;

    //------------ Constructor Method -----------------
    private Game() {
        player = Player.getInstance();
        player.setWeapon(new Weapon("Knife", 20));
        challenges = CustomFileReader.readChallenges("challenges.txt");
        userCommands = new ArrayList<>();
        commands = new ArrayList<>();
        rows = 3;
        cols = 3;
        roomMap = new Room[rows][cols];
        gameUI = new GameUI();
        gameUI.addObserver(this);
        gameRunning = true;
    }

    //------------ Singleton Method -----------------
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    //------------ Getter Methods -----------------
    public Player getPlayer() {
        return player;
    }

    //------------ Start Game Method -----------------
    public void startGame() {
        // Populate the room map
        populateRooms();
        // Add the player's commands to move
        playerCommands((Player) player);

        printCurrentRoom();

        gameUI.getInput();
    }
    
    //------------ Stop Game Method -----------------
    public void stopGame() {
        gameRunning = false;
        System.out.println("The game has ended.");
    }

    //------------ Game Running Check Method -----------------
    public boolean isGameRunning() {
        return gameRunning;
    }

    //------------ Populate Rooms Method -----------------
    public void populateRooms() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();


        for (IChallenge challenge : challenges) {
            if (challenge instanceof Puzzle) {
                puzzles.add((Puzzle) challenge);
            } else {
                enemies.add((Enemy) challenge);
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room;
                if ((row + col) % 3 == 0) {
                    int randomIndex = (int) (Math.random() * enemies.size());
                    Enemy challenge = enemies.get(randomIndex);
                    enemies.remove(randomIndex);
                    room = new PhysicalRoomBuilder()
                        .setDescription("A physical challenge room")
                        .addChallenge(challenge)
                        .build();
                } else if ((row + col) % 3 == 1) {
                    int randomIndex = (int) (Math.random() * puzzles.size());
                    Puzzle challenge = puzzles.get(randomIndex);
                    puzzles.remove(randomIndex);
                    room = new SkillRoomBuilder()
                        .setDescription("A skill challenge room")
                        .addChallenge(challenge)
                        .build();
                } else {
                    int randomIndexPuzzles = (int) (Math.random() * puzzles.size());
                    Puzzle puzzleChallenge = puzzles.get(randomIndexPuzzles);
                    puzzles.remove(randomIndexPuzzles);
                    int randomIndexEnemies = (int) (Math.random() * enemies.size());
                    Enemy enemyChallenge = enemies.get(randomIndexEnemies);
                    enemies.remove(randomIndexEnemies);
                    room = new UltimateRoomBuilder()
                        .setDescription("An ultimate challenge room")
                        .addChallenge(puzzleChallenge)
                        .addChallenge(enemyChallenge)
                        .build();
                }
                roomMap[row][col] = room;
            }
        }
    }

    //------------ Add Comands Method -----------------
    public void addCommand(String name, ICommand command) {
        userCommands.add(name.toLowerCase());
        commands.add(command);
    }

    //------------ Execute Commands Method -----------------
    public void executeCommands(String commandInput) {

        int index = -1;
        String normalizedInput = commandInput.toLowerCase();

        for (int i=0; i < userCommands.size(); i++) {
            if (userCommands.get(i).equals(normalizedInput)) {
                index = i;
                break;
            }       
        }

        if (index > -1) {
            this.commands.get(index).execute();
        } else {
            System.out.println("Command not recognized: " + commandInput);
        }
    }

    //------------ Players Commands Method -----------------
    private void playerCommands(Player player) {
        addCommand("S", this::startCurrentRoomChallenge);

        addCommand("Move right", () -> {
            if (areAllChallengesCompleted()) {
                player.resetHealth();
                player.moveRight();
            } else {
                System.out.println("You cannot move until all challenges in this room are completed.");
            }
        });

        addCommand("Move left", () -> {
            if (areAllChallengesCompleted()) {
                player.resetHealth();
                player.moveLeft();
            } else {
                System.out.println("You cannot move until all challenges in this room are completed.");
            }
        });
    
        addCommand("Move forward", () -> {
            if (areAllChallengesCompleted()) {
                player.resetHealth();
                player.moveForward();
            } else {
                System.out.println("You cannot move until all challenges in this room are completed.");
            }
        });
    
        addCommand("Move back", () -> {
            if (areAllChallengesCompleted()) {
                player.resetHealth();
                player.moveBack();
            } else {
                System.out.println("You cannot move until all challenges in this room are completed.");
            }
        });
    }

    //------------ Start Current Rooms Challenges Method -----------------
    private void startCurrentRoomChallenge() {

        if (!player.isAlive()) {
            System.out.println("Game Over! You are dead.");
            stopGame();
            return;
        }

        int[] currentRoom = player.getCurrentRoom(); 
        int row = currentRoom[0];
        int col = currentRoom[1];
    
        Room room = roomMap[row][col];
    
        room.startChallenge(player);
    }

    //------------ Are All Challenges In Current Room Complete Check Method -----------------
    private boolean areAllChallengesCompleted() {
        int[] currentRoom = player.getCurrentRoom(); 
        int row = currentRoom[0];
        int col = currentRoom[1];
        
        Room room = roomMap[row][col];
        for (IChallenge challenge : room.getChallenges()) {
            if (!challenge.isCompleted()) {
                return false; 
            }
        }
        return true; 
    }

    //------------ Are All Challenges In All Rooms Complete Check Method -----------------
    private boolean areAllChallengesCompletedInAllRooms() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room = roomMap[row][col];
                for (IChallenge challenge : room.getChallenges()) {
                    if (!challenge.isCompleted()) {
                        return false;  
                    }
                }
            }
        }
        return true;  
    }
    
    //------------ Print Current Room Method -----------------
    public void printCurrentRoom() {
        if (!player.isAlive()) {
            System.out.println("Game Over! You are dead.");
            stopGame();
            return;
        } else if (areAllChallengesCompletedInAllRooms()) {
            System.out.println("You have completed the game!");
            stopGame();
            return;
        }
        
        Player player = Player.getInstance();
        int[] currentRoom = player.getCurrentRoom();
        int row = currentRoom[0];
        int col = currentRoom[1];
        boolean allChallengesCompleted = false;
        Room room = roomMap[row][col];

        for (IChallenge challenge : room.getChallenges()) {
            if (!challenge.isCompleted()) {
                allChallengesCompleted = true;
                break; 
            }
        }

        System.out.println("You are in a room at (" + row + ", " + col + "): " + room.getDescription());
        System.out.println("    Challenges in this room:");
        for (IChallenge challenge : room.getChallenges()) {
            if (challenge.isCompleted() != true) {
                System.out.println("    - " + challenge.getDescription());
            }
        }
        if (!allChallengesCompleted) {
            System.out.println("    There are no more challenges in this room. Move to another room");
        }
    }

    //------------ Update Method -----------------
    @Override
    public void update(String commands) {
        executeCommands(commands);
        printCurrentRoom();
    }

    //------------ Print Rooms Method -----------------
    public void printRooms() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room = roomMap[row][col];
                System.out.println("Room at (" + row + ", " + col + "): " + room.getDescription());
                
                // Print challenges in rooms
                System.out.println("Challenges in this room:");
                for (IChallenge challenge : room.getChallenges()) {
                    if (challenge.isCompleted() != true) {
                        System.out.println(" - " + challenge.getDescription());
                        if (challenge instanceof Puzzle) {
                            Puzzle puzzle = (Puzzle) challenge;
                            System.out.println("   Puzzle answer: " + puzzle.getAnswer());
                        } else if (challenge instanceof Enemy) {
                            Enemy enemy = (Enemy) challenge;
                            System.out.println("   Enemy: " + enemy.getDescription() + ", Weapon: " + enemy.getWeapon().getDescription());
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    //------------ Summary Method -----------------
    public void summary() {

    }
}
