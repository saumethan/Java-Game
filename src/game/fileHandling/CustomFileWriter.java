package game.fileHandling;

import java.io.FileWriter;
import java.io.IOException;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 01/12/2024
 */

public class CustomFileWriter {

    public static void writeLeaderboard(String result) {
        try {
            FileWriter myWriter = new FileWriter("leaderBoard.txt", true);
            myWriter.write(result + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
