package game.fileHandling;
import game.challenges.*;
import game.combat.Weapon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class CustomFileReader {

    //------------ File Reading Method -----------------
    public static ArrayList<IChallenge> readChallenges(String filePath) {

        ArrayList<IChallenge> challenges = new ArrayList<>();

        try {
            File myFile = new File(filePath);
            Scanner myFileReader = new Scanner(myFile);

            while (myFileReader.hasNextLine()) {
                String data = myFileReader.nextLine().trim();

                String[] challengeItems = data.split(",");
                
                if (challengeItems.length == 3) {
                    String description = challengeItems[0].trim();
                    String question = challengeItems[1].trim();
                    String answer = challengeItems[2].trim();
                    challenges.add(new Puzzle(description, question, answer));
                } else {
                    String name = challengeItems[0].trim();
                    String description = challengeItems[1].trim();
                    String weaponDescription = challengeItems[2].trim();
                    String weaponBaseDamage = challengeItems[3].trim();
                    challenges.add(new Enemy(name, description, new Weapon(weaponDescription, Integer.parseInt(weaponBaseDamage))));
                }
                
            }

            myFileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occourred");
            e.printStackTrace();
        }
        return challenges;
    } 

    public static ArrayList<String> readLeaderboard(String filePath) {
        ArrayList<String> leaderboard = new ArrayList<>();
    
        try {
            File myFile = new File(filePath);
            Scanner myFileReader = new Scanner(myFile);
    
            while (myFileReader.hasNextLine()) {
                String data = myFileReader.nextLine().trim();
    
                leaderboard.add(data);
            }
    
            myFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred: File not found");
            e.printStackTrace();
        }
    
        return leaderboard;
    }

    public static ArrayList<Weapon> readPlayerWeapons(String filePath) {
        ArrayList<Weapon> weapons = new ArrayList<>();
    
        try {
            File myFile = new File(filePath);
            Scanner myFileReader = new Scanner(myFile);

            while (myFileReader.hasNextLine()) {
                String data = myFileReader.nextLine().trim();

                String[] weaponItems = data.split(",");
                
                if (weaponItems.length == 2) {
                    String description = weaponItems[0].trim();
                    String damage = weaponItems[1].trim();
                    weapons.add(new Weapon(description, Integer.parseInt(damage)));
                }
            }

            myFileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occourred");
            e.printStackTrace();
        }
        return weapons;
    }
    
    
}
