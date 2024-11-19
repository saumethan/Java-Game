public class Enemy implements IChallenge {

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
        return weapon.getDamage();
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void addHealth(int addedHealth) {
        health += addedHealth;
    }
}
