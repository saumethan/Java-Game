package game;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import game.challenges.Enemy;
import game.challenges.IChallenge;
import game.challenges.Puzzle;
import game.combat.Weapon;
import game.fileHandling.CustomFileReader;
import game.fileHandling.CustomFileWriter;
import game.room.PhysicalRoomBuilder;
import game.room.Room;
import game.room.SkillRoomBuilder;
import game.room.UltimateRoomBuilder;
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
    // https://www.w3schools.com/java/java_date.asp
    private LocalDateTime currentDateTime;
    private DateTimeFormatter dateFormater;
    private Boolean initialisingGame;
    private Boolean printCommand;
    private ArrayList<Weapon> weapons;

    //------------ Constructor Method -----------------
    private Game() {
        player = Player.getInstance();
        challenges = CustomFileReader.readChallenges("challenges.txt");
        userCommands = new ArrayList<>();
        commands = new ArrayList<>();
        rows = 3;
        cols = 3;
        roomMap = new Room[rows][cols];
        gameUI = new GameUI();
        gameUI.addObserver(this);
        gameRunning = true;
        dateFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        weapons = CustomFileReader.readPlayerWeapons("playerWeapons.txt");
        printCommand = false;
    }

    //------------ Singleton Get Instance Method -----------------
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
        initialisingGame = true;
        System.out.println("-----------------------------------------------");
        System.out.println("Please enter your name:");;
        gameUI.getInput();
    }
    
    //------------ Stop Game Method -----------------
    public void stopGame() {
        gameRunning = false;
        System.out.println("The game has ended.");
        addLeaderboard();
        printLeaderboard();
    }

    //------------ Add To Leaderboard Method -----------------
    public void addLeaderboard() {
        int totalAttempts = 0;
        int completedChallenges = 0;
        boolean gameCompleted = true;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (IChallenge challenge : roomMap[row][col].getChallenges()) {
                    if (challenge.getAttempts() > 0) {
                        totalAttempts += challenge.getAttempts();
                        gameCompleted = true;
                        if (challenge.isCompleted()) {
                            completedChallenges++; 
                        }
                    } else {
                        gameCompleted = false; 
                    }
                }
            }
        }
        currentDateTime = LocalDateTime.now();
        if (gameCompleted) {
            CustomFileWriter.writeLeaderboard("Name: " + player.getName() + "Date : " + currentDateTime.format(dateFormater) + ", Challenges completed: " + completedChallenges + ", Total Attempts: " + Integer.toString(totalAttempts));
            System.out.println("Total Attempts: " + totalAttempts);
        } else {
            CustomFileWriter.writeLeaderboard("Name: " + player.getName() + ", Date : " + currentDateTime.format(dateFormater) + ", Challenges completed: " + completedChallenges + ", Game Not Completed");
        }
        
    }

    //------------ Print Leaderboard Method -----------------
    public void printLeaderboard() {
        ArrayList<String> leaderboard = CustomFileReader.readLeaderboard("leaderBoard.txt");
        System.out.println("-------- Leaderboard --------");
        for (String entry : leaderboard) {
            System.out.println(entry);
        }
    }

    //------------ Game Running Check Method -----------------
    public Boolean isGameRunning() {
        return gameRunning;
    }

    //------------ Print Command Check Method -----------------
    public Boolean isPrintCommand() {
        return printCommand;
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
                        .setDescription("   You are in a dark dungeon ")
                        .addChallenge(challenge)
                        .build();
                } else if ((row + col) % 3 == 1) {
                    int randomIndex = (int) (Math.random() * puzzles.size());
                    Puzzle challenge = puzzles.get(randomIndex);
                    puzzles.remove(randomIndex);
                    room = new SkillRoomBuilder()
                        .setDescription("   You are in a dark dungeon ")
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
                        .setDescription("   You are in a dark dungeon ")
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
            listCommands();
        }
    }

    //------------ List Commands Method -----------------
    public void listCommands() {
        System.out.println("Available commands:");
        for (String command : userCommands) {
            System.out.println("- " + command);
        }
    }

    //------------ Players Commands Method -----------------
    private void playerCommands(Player player) {
        addCommand("Start challenge", this::startCurrentRoomChallenge);

        addCommand("Print leaderboard", this::printLeaderboard);

        addCommand("List", this::listCommands);

        addCommand("Move right", () -> {
            if (areAllChallengesCompleted()) {
                player.resetHealth();
                player.moveRight(rows);
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
                player.moveBack(cols);
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
    private Boolean areAllChallengesCompleted() {
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
    private Boolean areAllChallengesCompletedInAllRooms() {
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

    //------------ Ckeck if a String Is a Intager Method -----------------
    public static Boolean isInteger(String string) {
        try {
            Integer.parseInt(string); 
            return true; 
        } catch (NumberFormatException e) {
            return false; 
        }
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
        Boolean allChallengesCompleted = false;
        Room room = roomMap[row][col];

        for (IChallenge challenge : room.getChallenges()) {
            if (!challenge.isCompleted()) {
                allChallengesCompleted = true;
                break; 
            }
        }

        System.out.println("You are in a room at (" + row + ", " + col + "): \n" + room.getDescription());
        
        if (!allChallengesCompleted) {
            System.out.println("    There are no more challenges in this room. Move to another room");
        }
    }

    //------------ Update Method -----------------
    @Override
    public void update(String command) {
        if (initialisingGame) {
            String name = command.trim();
            if (name.isEmpty()) {
                name = "Guest";
            }
            player.setName(name);
            initialisingGame = false;
            System.out.println("Welcome, " + name + "!");
            
            if (player.getWeapon() == null) {
                System.out.println("Choose your weapon:");
                
                for (int i = 0; i < weapons.size(); i++) {
                    System.out.println((i + 1) + ". " + weapons.get(i).getDescription() + " (Damage: " + weapons.get(i).getBaseDamage() + ")");
                }
                
                System.out.println("Enter the number of your weapon choice: ");
                gameUI.getInput();
                return;
            }
        }

        printCommand = true;

        if (player.getWeapon() == null) {
            int choice;
            if (isInteger(command)) {
                choice = Integer.parseInt(command.trim()) - 1;
            } else {
                choice = 0;
            }
            if (choice >= 0 && choice < weapons.size()) {
                player.setWeapon(weapons.get(choice));
                System.out.println("You have chosen: " + player.getWeapon().getDescription());
                populateRooms();
                playerCommands(player);
                printCurrentRoom();
                return;
            } else {
                System.out.println("Invalid choice. Default weapon selected: " + weapons.get(0).getDescription());
                player.setWeapon(weapons.get(0));
            }
            
            populateRooms();
            playerCommands(player);
            printCurrentRoom();
            return;
        }

        executeCommands(command);
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
