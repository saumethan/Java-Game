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
        currentRoom[0] = 1;
        currentRoom[1] = 1;
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
        if (currentRoom[0] < 3) {
            currentRoom[0] += 1;
        } else {
            System.out.println("Can't move right");
        }
    }

    public void moveLeft() {
        if (currentRoom[0] > 0) {
            currentRoom[0] -= 1;
        } else {
            System.out.println("Can't move left");
        }

    }

    public void moveUp() {
        if (currentRoom[1] > 0) {
            currentRoom[1] -= 1;
        } else {
            System.out.println("Can't move up");
        }
    }

    public void moveDown() {
        if (currentRoom[1] < 3) {
            currentRoom[1] += 1;
        } else {
            System.out.println("Can't move down");
        }
    }

    public void answerChallenge(String answer, IChallenge challenge) {
        if (challenge instanceof Puzzle) {
            Puzzle puzzle = (Puzzle) challenge;
            puzzle.attempt(answer);
            if (puzzle.isCompleted()) {
                System.out.println("Correct answer!");
            } else {
                System.out.println("Wrong answer");
            }
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
