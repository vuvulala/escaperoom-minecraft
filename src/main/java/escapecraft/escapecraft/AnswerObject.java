package escapecraft.escapecraft;

public class AnswerObject {
    private String questionString;
    private Boolean isCorrect;

    public AnswerObject(String questionString, Boolean isCorrect) {
        this.questionString = questionString;
        this.isCorrect = isCorrect;
    }

    public String getText() { return this.questionString; }
    public Boolean IsCorrect() { return this.isCorrect; }
}