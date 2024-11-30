package game;
import java.util.ArrayList;
import game.challenges.Enemy;
import game.challenges.IChallenge;
import game.challenges.Puzzle;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class UltimateRoomBuilder {

    private String description;
    private ArrayList<IChallenge> challenges;

    public UltimateRoomBuilder() {
        challenges = new ArrayList<>();
    }
    public UltimateRoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public UltimateRoomBuilder addChallenge(Puzzle puzzle) {
        challenges.add(puzzle);
        return this;
    }

    public UltimateRoomBuilder addChallenge(Enemy enemy) {
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