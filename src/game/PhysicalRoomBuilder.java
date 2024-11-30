package game;
import java.util.ArrayList;
import game.challenges.Enemy;
import game.challenges.IChallenge;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class PhysicalRoomBuilder {

    private String description;
    private ArrayList<IChallenge> challenges;

    public PhysicalRoomBuilder() {
        challenges = new ArrayList<>();
    }

    public PhysicalRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PhysicalRoomBuilder addChallenge(Enemy enemy) {
        challenges.add(enemy);
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


