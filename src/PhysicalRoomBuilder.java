/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

public class PhysicalRoomBuilder extends RoomBuilder {
    public PhysicalRoomBuilder addPhysicalChallenge(Enemy enemy) {
        challenges.add(enemy);
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

