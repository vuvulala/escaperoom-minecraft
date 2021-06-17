package escapecraft.escapecraft;

public class AnswerObject {
    private String questionString;
    private Boolean isCorrect;

    AnswerObject(String questionString, Boolean isCorrect) {
        this.questionString = questionString;
        this.isCorrect = isCorrect;
    }

    public String GetQuestion() { return this.questionString; }
    public Boolean IsCorrect() { return this.isCorrect; }
}
