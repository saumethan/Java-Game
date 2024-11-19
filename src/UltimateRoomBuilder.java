/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class UltimateRoomBuilder extends RoomBuilder {
    public UltimateRoomBuilder addSkillChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
        return this;
    }

    public UltimateRoomBuilder addPhysicalChallenge(Enemy enemy) {
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