/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;
import java.util.Scanner;

import challenges.Enemy;
import challenges.IChallenge;
import challenges.Puzzle;

public class Room {

    // Variables
    private String description;
    private ArrayList<IChallenge> challenges;

    public void addChallenge(IChallenge challenge) {
        challenges.add(challenge);
    }
    
    public Room(String description) {
        this.description = description;
        challenges = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void startChallenge(Player player) {
        Scanner scanner = new Scanner(System.in);
        for (IChallenge challenge : challenges) {
            if (challenge instanceof Puzzle && challenge.isCompleted() != true) {
                Puzzle puzzle = (Puzzle) challenge;
                System.out.println("You've encounter a puzzle: " + puzzle.getDescription());
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine();
                if (puzzle.attempt(userAnswer)) {
                    System.out.println("Correct answer!");
                    challenge.setCompleted();
                    break;
                } else {
                    System.out.println("Wrong answer.");
                }
            } else if (challenge instanceof Enemy && challenge.isCompleted() != true) {
                Enemy enemy = (Enemy) challenge;
                System.out.println("You've encounter an enemy: " + enemy.getDescription());
                System.out.print("Type fight to start: ");
                String action = scanner.nextLine();
                if (action.trim().toLowerCase().equals("fight")) {
                    while (player.isAlive() && !enemy.isCompleted()) {
                        enemy.takeDamage(player.attack());
                        player.takeDamage(enemy.attack());
                    }
                    if (player.isAlive() && enemy.isCompleted()) {
                        System.out.println("You have killed the " + enemy.getDescription());
                        break;
                    } else {
                        System.out.println("You have died");
                    }
                }
            }
        } 
    }
    
    public ArrayList<IChallenge> getChallenges() {
        return challenges;
    }
}


