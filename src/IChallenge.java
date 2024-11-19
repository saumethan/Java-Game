public interface IChallenge {

    Boolean isCompleted();
    void attempt(int amount);
    String getDescription();
}
