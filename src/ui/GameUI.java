package ui;

import java.util.ArrayList;

public class GameUI {

    //Variables 
    private ArrayList<UIObserver> observers;

    public GameUI() {

        observers = new ArrayList<>();
    }

    public void addObserver(UIObserver UIObserver) {
        observers.add(UIObserver);
    }

    private void notifyObservers(String input) {
        for (UIObserver UIObserver : observers) {
            UIObserver.update(input);
        }
    }

    public void getInput() {
    }

}
