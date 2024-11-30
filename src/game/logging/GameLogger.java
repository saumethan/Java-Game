package game.logging;
import game.ui.UIObserver;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class GameLogger implements UIObserver {

    public void update(String command) {
        System.out.println("Entered command: " + command);
    }

}
