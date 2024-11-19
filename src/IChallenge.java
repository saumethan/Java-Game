public interface IChallenge {

    Boolean isCompleted();
    void attempt(int action);
    String getDescription();
}
