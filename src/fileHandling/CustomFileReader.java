package fileHandling;

import challenges.*;
import combat.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomFileReader {

    public static ArrayList<IChallenge> readChallenges(String filePath) {

        ArrayList<IChallenge> challenges = new ArrayList<>();

        try {
            File myFile = new File(filePath);
            Scanner myFileReader = new Scanner(myFile);

            while (myFileReader.hasNextLine()) {
                String data = myFileReader.nextLine().trim();

                String[] challengeItems = data.split(",");
                
                if (challengeItems.length == 2) {
                    String question = challengeItems[0].trim();
                    String answer = challengeItems[1].trim();
                    challenges.add(new Puzzle(question, answer));
                } else {
                    String description = challengeItems[0].trim();
                    String weaponDescription = challengeItems[1].trim();
                    String weaponBaseDamage = challengeItems[2].trim();
                    challenges.add(new Enemy(description, new Weapon(weaponDescription, Integer.parseInt(weaponBaseDamage))));
                }
                
            }

            myFileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occourred");
            e.printStackTrace();
        }
        return challenges;
    } 
}
