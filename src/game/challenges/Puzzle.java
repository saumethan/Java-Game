package game.challenges;

/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 30/11/2024
 */
public class Puzzle implements IChallenge {

    //------------ Variables ----------------- 
    private String description;
    private String question;
    private String answer;
    private int attemps;
    private boolean isCompleted;

    //------------ Constructor Method -----------------
    public Puzzle(String description, String question, String answer) {
        this.description = description;
        this.question = question;
        this.answer = answer;
        attemps = 0;
        isCompleted = false;
    }

    //------------ Getter Methods -----------------
    public String getDescription() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAttemps() {
        return attemps;
    }

    //------------ Attempt Method -----------------
    public Boolean attempt(String amount) {
        attemps++;
        if (amount.equals(answer)) {
            return true;
        } else {
            return false;
        }
    }

    //------------ Is Challenge Complete Check Method -----------------
    public Boolean isCompleted() {
        return isCompleted;
    }

    //------------ Set Completed Method -----------------
    public void setCompleted() {
        isCompleted = true;
    };
}
