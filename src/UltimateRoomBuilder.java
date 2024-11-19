/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class UltimateRoomBuilder extends RoomBuilder {

    private Puzzle puzzle;
    private Enemy enemy;

    public UltimateRoomBuilder addSkillChallenge(Puzzle puzzle) {
        this.puzzle = puzzle;
        return this;
    }

    public UltimateRoomBuilder addPhysicalChallenge(Enemy enemy) {
        this.enemy = enemy;
        return this;
    }

    @Override
    public Room build() {
        return new Room(description, puzzle, enemy);
    }
}


