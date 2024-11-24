/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;
import java.util.Scanner;

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

    public void startChallenge() {
        for (IChallenge challenge : challenges) {
            if (challenge instanceof Puzzle) {
                Puzzle puzzle = (Puzzle) challenge;
                System.out.println("You encounter a puzzle: " + puzzle.getDescription());
                Scanner scanner = new Scanner(System.in); 
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine();
                if (puzzle.attempt(userAnswer)) {
                    System.out.println("Correct answer!");
                    challenge.setCompleted();
                } else {
                    System.out.println("Wrong answer.");
                }
            }
        }
    }
<<<<<<< HEAD
    
    public ArrayList<IChallenge> getChallenges() {
        return challenges;
    }
}


=======

    public ArrayList<IChallenge> getChallenges() {
        return challenges;
    }
}
>>>>>>> b95598583fda32f0c769c42211750c68dbc86fa8
