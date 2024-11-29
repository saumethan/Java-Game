package game.logging;

import game.ui.UIObserver;

public class GameLogger implements UIObserver {

    public void update(String command) {
        System.out.println("Entered command: " + command);
    }

}
