/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;
import java.util.Random;

public class Game {

    // Variables 
    private Player player;
    private Room[][] room ; 
    private ArrayList<IChallenge> challenges;
    private ArrayList<String> userCommands;
    private ArrayList<ICommand> commands;

    // Constructor
    public Game() {
        player = new Player();
        challenges = new ArrayList<>();
        userCommands = new ArrayList<>();
        commands = new ArrayList<>();
    }

    public void startGame() {

    }

    public void executeCommands() {

    }

    public void update() {

    }

    public void summary() {

    }
}
