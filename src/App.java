import game.Game;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public class App {
    public static void main(String[] args) throws Exception {
        // Get Instance of Game
        Game game = Game.getInstance();
        // Start game
        game.startGame();
    }
}
