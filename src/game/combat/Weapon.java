package game.combat;
/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

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
