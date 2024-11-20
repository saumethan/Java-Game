/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;
import java.util.Arrays;
import ui.GameUI;
import ui.UIObserver;

public class Game implements UIObserver {

    // Variables 
    private Player player;
    private Room[][] roomMap; 
    private ArrayList<IChallenge> challenges;
    private ArrayList<String> userCommands;
    private ArrayList<ICommand> commands;
    private int rows;
    private int cols;
    private GameUI gameUI;
    private Boolean gameRunning;

    // Constructor
    public Game() {
        player = Player.getInstance();
        challenges = new ArrayList<>();
        userCommands = new ArrayList<>();
        commands = new ArrayList<>();
        rows = 3;
        cols = 3;
        roomMap = new Room[rows][cols];
        gameUI = new GameUI();
        gameUI.addObserver(this);
        gameRunning = true;
    }

    public void stopGame() {
        gameRunning = false;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void populateRooms() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room;
                if ((row + col) % 3 == 0) {
                    room = new PhysicalRoomBuilder()
                        .setDescription("A physical challenge room")
                        .addChallenge(new Enemy("Ogre", new Weapon("Sword", 10)))
                        .build();
                } else if ((row + col) % 3 == 1) {
                    room = new SkillRoomBuilder()
                        .setDescription("A skill challenge room")
                        .addChallenge(new Puzzle("What is 5 + 3?", "8"))
                        .build();
                } else {
                    room = new UltimateRoomBuilder()
                        .setDescription("An ultimate challenge room")
                        .addChallenge(new Puzzle("What is 7 * 6?", "42"))
                        .addChallenge(new Enemy("Ogre", new Weapon("Sword", 10)))
                        .build();
                }
                roomMap[row][col] = room;
            }
        }
    }

    public void startGame() {
        // Populate the room map
        populateRooms();
        // Add the players commands to move
        playerCommands((Player) player);

        printCurrentRoom();

        gameUI.getInput();

        printCurrentRoom();
    }

    public void addCommand(String name, ICommand command) {
        userCommands.add(name.toLowerCase());
        commands.add(command);
    }


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

    private void startCurrentRoomChallenge() {
        int[] currentRoom = player.getCurrentRoom(); 
        int row = currentRoom[0];
        int col = currentRoom[1];
    
        Room room = roomMap[row][col];
    
        room.startChallenge();
    }

    private void playerCommands(Player player) {
        addCommand("Start challenge", this::startCurrentRoomChallenge);
        addCommand("Move right", player::moveRight);
        addCommand("Move left", player::moveLeft);
        addCommand("Move up", player::moveUp);
        addCommand("Move down", player::moveDown);
    }

    public void printRooms() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room = roomMap[row][col];
                System.out.println("Room at (" + row + ", " + col + "): " + room.getDescription());
                
                // Print challenges in rooms
                System.out.println("Challenges in this room:");
                for (IChallenge challenge : room.getChallenges()) {
                    System.out.println(" - " + challenge.getDescription());
                    if (challenge instanceof Puzzle) {
                        Puzzle puzzle = (Puzzle) challenge;
                        System.out.println("   Puzzle answer: " + puzzle.getAnswer());
                    } else if (challenge instanceof Enemy) {
                        Enemy enemy = (Enemy) challenge;
                        System.out.println("   Enemy: " + enemy.getDescription() + ", Weapon: " + enemy.getWeapon().getDescription());
                    }
                }
                System.out.println();
            }
        }
    }

    @Override
    public void update(String commands) {
        executeCommands(commands);
        printCurrentRoom();
    }

    public void printCurrentRoom() {
        Player player = Player.getInstance();
        int[] currentRoom = player.getCurrentRoom();
        int row = currentRoom[0];
        int col = currentRoom[1];

        Room room = roomMap[row][col];
        System.out.println("You are in a room at (" + row + ", " + col + "): " + room.getDescription());
        System.out.println("Challenges in this room:");
        for (IChallenge challenge : room.getChallenges()) {
            System.out.println(" - " + challenge.getDescription());
        }
        System.out.println(Arrays.toString(player.getCurrentRoom()));
    }

    public void summary() {

    }
}
