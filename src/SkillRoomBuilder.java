/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

public class SkillRoomBuilder extends RoomBuilder {
    public SkillRoomBuilder addSkillChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
        return this;
    }

    @Override
    public Room build() {
        Room room = new Room(description);
        for (IChallenge challenge : challenges) {
            room.addChallenge(challenge);
        }
        return room;
    }
}

