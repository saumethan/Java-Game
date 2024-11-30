package game.room;
import java.util.ArrayList;

import game.challenges.Enemy;
import game.challenges.IChallenge;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class PhysicalRoomBuilder {

    //------------ Variables -----------------
    private String description;
    private ArrayList<IChallenge> challenges;

    //------------ Constructor Method -----------------
    public PhysicalRoomBuilder() {
        challenges = new ArrayList<>();
    }

    //------------ Set Description Method -----------------
    public PhysicalRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    //------------ Add Enemy Challenge Method -----------------
    public PhysicalRoomBuilder addChallenge(Enemy enemy) {
        challenges.add(enemy);
        return this;
    }

    //------------ Build Room Method -----------------
    public Room build() {
        Room room = new Room(description);
        for (IChallenge challenge : challenges) {
            room.addChallenge(challenge);
        }
        return room;
    }
}


