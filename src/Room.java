/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class Room {

    // Variables
    private String description;
    private Puzzle puzzle; // Null for rooms without puzzles
    private Enemy enemy; // Null for rooms without enimies
    
    public Room(String description, Puzzle puzzle, Enemy enemy) {
        this.description = description;
        this.puzzle = puzzle;
        this.enemy = enemy;
    }

    public String getDescription() {
        return description;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
