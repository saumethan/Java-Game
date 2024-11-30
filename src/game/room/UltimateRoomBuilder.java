package game.room;
import java.util.ArrayList;

import game.challenges.Enemy;
import game.challenges.IChallenge;
import game.challenges.Puzzle;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class UltimateRoomBuilder {

    //------------ Variables -----------------
    private String description;
    private ArrayList<IChallenge> challenges;

    //------------ Constructor Method -----------------
    public UltimateRoomBuilder() {
        challenges = new ArrayList<>();
    }

    //------------ Set Description Method -----------------
    public UltimateRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    //------------ Add Puzzle Challenge Method -----------------
    public UltimateRoomBuilder addChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
        return this;
    }

    //------------ Add Enemy Challenge Method -----------------
    public UltimateRoomBuilder addChallenge(Enemy enemy) {
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