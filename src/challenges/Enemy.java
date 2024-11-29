package challenges;
/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import combat.Weapon;
import health.IHealth;

public class Enemy implements IChallenge, IHealth {

    // Variables
    private String description;
    private Weapon weapon;
    private int health;
    private Boolean isCompleted;

    public Enemy(String description, Weapon weapon) {
        this.description = description;
        this.weapon = weapon;
        health = 100;
        isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getBonusDamage() {
        return (int) (Math.random() * 21);
    }

    public void attempt(int amount) {
        takeDamage(amount);
    }

    public void takeDamage(int amount) {
        if (health - amount > 0) {
            health-=amount;
        } else {
            isCompleted = true;
        }
    }

    public int attack() {
        return weapon.getBaseDamage() + getBonusDamage();
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
    };

    public void heal(int healAmount) {
        if (health + healAmount < 100) {
            health += healAmount;
        } else {
            health = 100;
        }
    }
}
