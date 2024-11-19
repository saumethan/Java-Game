/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

public class Player {

    // Variables
    private Weapon weapon;
    private int health;
    private int level;
    private Boolean isAlive;
    private static Player instance;

    private Player(){
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
