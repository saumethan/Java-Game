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
    private int attempts;
    private boolean isCompleted;

    //------------ Constructor Method -----------------
    public Puzzle(String description, String question, String answer) {
        this.description = description;
        this.question = question;
        this.answer = answer;
        attempts = 0;
        isCompleted = false;
    }

    //------------ Getter Methods -----------------
    public String getDescription() {
        return description;
    }

    public String getChallengeDescription() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAttempts() {
        return attempts;
    }

    //------------ Attempt Method -----------------
    public Boolean attempt(String amount) {
        attempts++;
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
