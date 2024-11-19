/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

//import java.util.ArrayList;

public class Game {

    // Variables 
    //private Player player;
    private Room[][] roomMap ; 
    //private ArrayList<IChallenge> challenges;
    //private ArrayList<String> userCommands;
    //private ArrayList<ICommand> commands;
    private int rows;
    private int cols;

    // Constructor
    public Game() {
        // player = Player.getInstance();
        // challenges = new ArrayList<>();
        // userCommands = new ArrayList<>();
        // commands = new ArrayList<>();
        rows = 3;
        cols = 3;
        roomMap = new Room[rows][cols];
    }

    public void populateRooms() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Room room;
                if ((row + col) % 3 == 0) {
                    room = new PhysicalRoomBuilder()
                        .setDescription("A physical challenge room at (" + row + ", " + col + ")")
                        .addChallenge(new Enemy("Ogre", new Weapon("Sword", 10)))
                        .build();
                } else if ((row + col) % 3 == 1) {
                    room = new SkillRoomBuilder()
                        .setDescription("A skill challenge room at (" + row + ", " + col + ")")
                        .addChallenge(new Puzzle("What is 5 + 3?", "8"))
                        .build();
                } else {
                    room = new UltimateRoomBuilder()
                        .setDescription("An ultimate challenge room at (" + row + ", " + col + ")")
                        .addChallenge(new Puzzle("What is 7 * 6?", "42"))
                        .addChallenge(new Enemy("Ogre", new Weapon("Sword", 10)))
                        .build();
                }
                roomMap[row][col] = room;
            }
        }
    }

    public void startGame() {

    }

    public void executeCommands() {

    }

    public void update() {

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

    public void summary() {

    }
}
