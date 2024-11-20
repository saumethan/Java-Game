/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

public class SkillRoomBuilder {

    private String description;
    private ArrayList<IChallenge> challenges;

    public SkillRoomBuilder() {
        challenges = new ArrayList<>();
    }

    public SkillRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SkillRoomBuilder addChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
        return this;
    }

    public Room build() {
        Room room = new Room(description);
        for (IChallenge challenge : challenges) {
            room.addChallenge(challenge);
        }
        return room;
    }
}

