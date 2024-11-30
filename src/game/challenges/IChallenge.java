package game.challenges;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */

public interface IChallenge {

    Boolean isCompleted();
    void setCompleted();
    //void attempt(int amount);
    String getDescription();
}
