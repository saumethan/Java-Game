package game;
import game.combat.Weapon;
import game.health.IHealth;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 02/12/2024
 */

public class Player implements IHealth {

    //------------ Variables -----------------
    private String name;
    private int[] currentRoom;
    private Weapon weapon;
    private int health;
    private Boolean isAlive;
    private int lives;
    private static Player instance;

    //------------ Constructor Method -----------------
    private Player() {
        name = "Guest";
        currentRoom = new int[2];
        currentRoom[0] = 1;
        currentRoom[1] = 1;
        health = 100;
        isAlive = true;
        lives = 3;
    }

    //------------ Singleton Get Instance Method -----------------
    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    //------------ Getter Methods -----------------
    public int getHealth() {
        return health;
    }

    public int[] getCurrentRoom() {
        return currentRoom;
    }

    public int getBonusDamage() {
        return (int) (Math.random() * 21);
    }

    public int getLives() {
        return lives;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    //------------ Setter Methods -----------------
    public void setAlive() {
        isAlive = true;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setName(String name) {
        this.name = name;
    }

    //------------ Remove Life Method -----------------
    public void removeLife() {
        lives--;
        if (lives <= 0) {
            isAlive = false;
        }
    }

    //------------ Is Alive Check Method -----------------
    public Boolean isAlive() {
        return isAlive;
    }

    //------------ Reset Health Method -----------------
    public void resetHealth() {
        health = 100;
    }

    //------------ Take Damage Method -----------------
    public void takeDamage(int amount) {
        if (health - amount > 0) {
            health-=amount;
        } else {
            isAlive = false;
        }
    }

    //------------ Movement Methods -----------------
    public void moveRight(int rows) {
        if (currentRoom[0] < rows - 1) {
            currentRoom[0] += 1;
        } else {
            System.out.println("There are no rooms to the right");
        }
    }

    public void moveLeft() {
        if (currentRoom[0] > 0) {
            currentRoom[0] -= 1;
        } else {
            System.out.println("There are no rooms to the left");
        }

    }

    public void moveForward() {
        if (currentRoom[1] > 0) {
            currentRoom[1] -= 1;
        } else {
            System.out.println("There are no rooms in front");
        }
    }

    public void moveBack(int cols) {
        if (currentRoom[1] < cols - 1) {
            currentRoom[1] += 1;
        } else {
            System.out.println("There are no rooms behind");
        }
    }

    //------------ Attack Method -----------------
    public int attack() {
        return weapon.getBaseDamage() + getBonusDamage();
    }

    //------------ Heal Method -----------------
    public void heal(int healAmount) {
        if (health + healAmount < 100) {
            health += healAmount;
        } else {
            health = 100;
        }
    }
}
