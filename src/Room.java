/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

public class Room {

    // Variables
    private String description;
    private ArrayList<ICHallenge> challenges;

    public Room(String description) {
        this.description = description;
        challenges = new ArrayList<>();
    }

    public String getDescripString() {
        return description;
    }

    public ArrayList<ICHallenge> getChallenges() {
        return challenges;
    }
}
