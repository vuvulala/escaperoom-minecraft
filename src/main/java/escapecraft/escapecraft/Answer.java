package escapecraft.escapecraft;

public class Answer {
    public Answer(int questionNumber, int answerNumber, boolean answerBoolean) {
        questionNum = questionNumber;
        answerNum = answerNumber;
        correct = answerBoolean;
    }

    public int questionNum;
    public int answerNum;
    public boolean correct;
}
