package game.room;
import java.util.ArrayList;
import java.util.Scanner;

import game.Player;
import game.challenges.Enemy;
import game.challenges.IChallenge;
import game.challenges.Puzzle;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class Room {

    //------------ Variables -----------------
    private String description;
    private ArrayList<IChallenge> challenges;

    //------------ Constructor Method -----------------
    public Room(String description) {
        this.description = description;
        challenges = new ArrayList<>();
    }

    //------------ Getter Methods -----------------
    public String getDescription() {
        String output = description;
        for (int i = 0; i < challenges.size(); i++) {
            if (!challenges.get(i).isCompleted()) {
                output += "with a ";
                output += challenges.get(i).getDescription();
                if (i < challenges.size() - 1) { 
                    output += " and ";
                }
            }
        }
        return output;
    }

    public ArrayList<IChallenge> getChallenges() {
        return challenges;
    }

    //------------ Add Challenge Method -----------------
    public void addChallenge(IChallenge challenge) {
        challenges.add(challenge);
    }
    
    //------------ Start Challenge Method -----------------
    public void startChallenge(Player player) {
        Scanner scanner = new Scanner(System.in);
        for (IChallenge challenge : challenges) {
            if (challenge instanceof Puzzle && challenge.isCompleted() != true) {
                Puzzle puzzle = (Puzzle) challenge;
                System.out.println("You've encounter a " + puzzle.getDescription());
                System.out.println("Question: " + puzzle.getChallengeDescription());
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine();
                if (puzzle.attempt(userAnswer)) {
                    System.out.println("Correct answer!");
                    challenge.setCompleted();
                    break;
                } else {
                    System.out.println("Wrong answer. You have lost a life");
                    player.removeLife();
                    System.out.println("You have " + player.getLives() + " lives left");
                    break;
                }
            } else if (challenge instanceof Enemy && challenge.isCompleted() != true) {
                Enemy enemy = (Enemy) challenge;
                System.out.println("You've encounter a " + enemy.getDescription());
                System.out.println("Name: " + enemy.getChallengeDescription());
                System.out.println("Your base damage: " + player.getWeapon().getBaseDamage());
                System.out.println("Enemy's base damage: " + enemy.getWeapon().getBaseDamage());
                System.out.print("Type \"fight\" to start: ");
                String action = scanner.nextLine();
                if (action.trim().toLowerCase().equals("fight")) {
                    while (player.isAlive() && !enemy.isCompleted()) {
                        enemy.takeDamage(player.attack());
                        if (!enemy.isCompleted()) {
                            player.takeDamage(enemy.attack());
                        }
                    }
                    if (player.isAlive() && enemy.isCompleted()) {
                        System.out.println("You have killed the " + enemy.getDescription());
                        enemy.addAttempt();
                        break;
                    } else {
                        player.setAlive();
                        System.out.println("You have lost the fight and a life");
                        player.removeLife();
                        System.out.println("You have " + player.getLives() + " lives left");
                        break;
                    }
                }
            }
        }
    }
}


