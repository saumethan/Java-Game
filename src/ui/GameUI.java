package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class GameUI {

    //Variables 
    private ArrayList<UIObserver> observers;

    public GameUI() {

        observers = new ArrayList<>();
    }

    public void addObserver(UIObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String input) {
        for (UIObserver observer : observers) {
            observer.update(input);  
        }
    }

    public void getInput() {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("Enter a command:");
            String input = scanner.nextLine().trim().toLowerCase(); 
    
            if (input.equals("exit")) {
                System.out.println("Exiting the game.");
                break;
            }
            notifyObservers(input);
        }
        scanner.close();
    }
    
}
