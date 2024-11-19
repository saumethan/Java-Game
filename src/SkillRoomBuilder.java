/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class SkillRoomBuilder extends RoomBuilder {

    private Puzzle puzzle;

    public SkillRoomBuilder addSkillChallenge(Puzzle puzzle) {
        this.puzzle = puzzle;
        return this;
    }

    @Override
    public Room build() {
        return new Room(description, puzzle, null);
    }
}

