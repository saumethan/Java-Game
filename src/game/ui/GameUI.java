package game.ui;
import java.util.ArrayList;
import java.util.Scanner;
import game.Game; 

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class GameUI {

    //------------ Variables -----------------
    private ArrayList<UIObserver> observers;
    Scanner scanner = new Scanner(System.in);

    //------------ Constructor Method -----------------
    public GameUI() {
        observers = new ArrayList<>();
    }

    //------------ Add Observer Method -----------------
    public void addObserver(UIObserver observer) {
        observers.add(observer);
    }

    //------------ Notify Observers Method -----------------
    private void notifyObservers(String input) {
        for (UIObserver observer : observers) {
            observer.update(input);  
        }
    }

    // //------------ Input To Set Players Name Method -----------------
    // public String setPlayerName() {
    //     System.out.println("Enter your name:");
    //     String name = scanner.nextLine().trim();
    //     return name;
    // }

    //------------ Get Input Method -----------------
    public void getInput() {
        Game game = Game.getInstance();
        if (!Game.getInstance().isGameRunning() || !Game.getInstance().getPlayer().isAlive()) {
            return; 
        }
    
        while (true) {
            
            if (!Game.getInstance().isGameRunning() || !Game.getInstance().getPlayer().isAlive()) {
                game.addLeaderboard(); 
                break;  
            }
    
            System.out.println("Enter a command:");
            String input = scanner.nextLine().trim().toLowerCase(); 
    
            if (input.equals("exit")) {
                System.out.println("Exiting the game.");
                game.stopGame();
                break;
            }
    
            notifyObservers(input);
        }
        scanner.close();  
    }
}
