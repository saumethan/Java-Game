/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class Player implements IHealth {

    // Variables
    private int[] currentRoom;
    private Weapon weapon;
    private int health;
    private int level;
    private Boolean isAlive;
    private static Player instance;

    private Player() {
        currentRoom = new int[2];
        currentRoom[0] = 2;
        currentRoom[1] = 2;
        health = 100;
        level = 1;
        isAlive = true;
    }

    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public int[] getCurrentRoom() {
        return currentRoom;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void takeDamage(int amount) {
        if (health - amount > 0) {
            health-=amount;
        } else {
            isAlive = false;
        }
    }

    public void moveRight() {
        currentRoom[0] += 1;
    }

    public void moveLeft() {
        currentRoom[0] -= 1;
    }

    public void moveUp() {
        currentRoom[1] -= 1;
    }

    public void moveDown() {
        currentRoom[1] += 1;
    }

    public int attack() {
        return weapon.getBaseDamage();
    }

    public void heal(int healAmount) {
        if (health + healAmount < 100) {
            health += healAmount;
        } else {
            health = 100;
        }
    }
}
