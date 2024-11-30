package game.room;
import java.util.ArrayList;

import game.challenges.IChallenge;
import game.challenges.Puzzle;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class SkillRoomBuilder {

    //------------ Variables -----------------
    private String description;
    private ArrayList<IChallenge> challenges;

    //------------ Constructor Method -----------------
    public SkillRoomBuilder() {
        challenges = new ArrayList<>();
    }

    //------------ Set Description Method -----------------
    public SkillRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    //------------ Add Puzzle Challenge Method -----------------
    public SkillRoomBuilder addChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
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

