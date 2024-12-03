package game.challenges;

import game.combat.Weapon;
import game.health.IHasHealth;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class Enemy implements IChallenge, IHasHealth {

    //------------ Variables -----------------
    private String name;
    private String description;
    private Weapon weapon;
    private int health;
    private Boolean isCompleted;
    private int attempts; 

    //------------ Constructor Method -----------------
    public Enemy(String name, String description, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.weapon = weapon;
        health = INITIAL_HEALTH;
        isCompleted = false;
        attempts = 0;
    }

    //------------ Getter Methods -----------------
    public String getDescription() {
        return description;
    }

    public String getChallengeDescription() {
        return name;
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

    public int getAttempts() {
        return attempts;
    }

    //------------ Setter Methods -----------------
    public void setCompleted() {
        isCompleted = true;
    };
    
    //------------ Attempt Method -----------------
    public void attempt(int amount) {
        takeDamage(amount);
    }

    //------------ Add Attempt Method -----------------
    public void addAttempt() {
        attempts++;
    }
    //------------ Take Damage Method -----------------
    public void takeDamage(int amount) {
        if (health - amount > 0) {
            health-=amount;
        } else {
            health = 0;
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
}
