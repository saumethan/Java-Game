public class Puzzle implements IChallenge {

    // Variables 
    private String description;
    private String question;
    private String answer;
    private int attemps;
    private boolean isCompleted;

    public Puzzle(String question, String answer) {
        this.question = question;
        this.answer = answer;
        attemps = 0;
        isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public String getQuestion() {
        return question;
    }

    public int getAttemps() {
        return attemps;
    }

    public void attempt(int amount) {
        attemps++;
        if (amount == Integer.parseInt(answer)) {
            isCompleted = true;
        }
    }

    public Boolean isCompleted() {
        return isCompleted;
    }
}
