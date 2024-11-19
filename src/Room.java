/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

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

    public String getDescripString() {
        return description;
    }

    public ArrayList<IChallenge> getChallenges() {
        return challenges;
    }
}