package fileHandling;

import challenges.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomFileReader {

    public static ArrayList<Puzzle> readChallenges(String filePath) {

        ArrayList<Puzzle> challenges = new ArrayList<>();

        try {
            File myFile = new File(filePath);
            Scanner myFileReader = new Scanner(myFile);

            while (myFileReader.hasNextLine()) {
                String data = myFileReader.nextLine().trim();

                String[] challengeItems = data.split(",", 3);
                
                String question = challengeItems[0].trim();
                String answer = challengeItems[1].trim();

                challenges.add(new Puzzle(question, answer));
            }

            myFileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occourred");
            e.printStackTrace();
        }
        return challenges;
    } 
}
