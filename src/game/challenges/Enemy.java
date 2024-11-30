package game.challenges;
import game.combat.Weapon;
import game.health.IHealth;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class Enemy implements IChallenge, IHealth {

    //------------ Variables -----------------
    private String description;
    private Weapon weapon;
    private int health;
    private Boolean isCompleted;

    //------------ Constructor Method -----------------
    public Enemy(String description, Weapon weapon) {
        this.description = description;
        this.weapon = weapon;
        health = 100;
        isCompleted = false;
    }

    //------------ Getter Methods -----------------
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

    //------------ Setter Methods -----------------
    public void setCompleted() {
        isCompleted = true;
    };
    
    //------------ Attempt Method -----------------
    public void attempt(int amount) {
        takeDamage(amount);
    }

    //------------ Take Damage Method -----------------
    public void takeDamage(int amount) {
        if (health - amount > 0) {
            health-=amount;
        } else {
            isCompleted = true;
        }
    }

    //------------ Attack Method -----------------
    public int attack() {
        return weapon.getBaseDamage() + getBonusDamage();
    }

    //------------ Is Challenge Complete Check Method -----------------
    public Boolean isCompleted() {
        return isCompleted;
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
