/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class PhysicalRoomBuilder extends RoomBuilder {

    private Enemy enemy;

    public PhysicalRoomBuilder addPhysicalChallenge(Enemy enemy) {
        this.enemy = enemy;
        return this;
    }

    @Override
    public Room build() {
        return new Room(description, null, enemy);
    }
}

