package game.fileHandling;

import java.io.FileWriter;
import java.io.IOException;

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
