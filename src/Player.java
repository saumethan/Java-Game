/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class Player {

    // Variables
    private String name;
    private Weapon weapon;
    private int health;
    private int level;
    private static Player instance;

    private Player(){
        health = 100;
        level = 1;
    }

    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }
}
