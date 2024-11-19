public class Weapon {

    //Variables
    private String description;
    private int baseDamage;

    public Weapon(String description, int baseDamage) {
        this.description = description;
        this.baseDamage = baseDamage;
    }

    public String getDescription() {
        return description;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
