package escapecraft.escapecraft;

public class QuestionObject {
    private String questionString;
    private Boolean isCorrect;

    QuestionObject(String questionString, Boolean isCorrect) {
        this.questionString = questionString;
        this.isCorrect = isCorrect;
    }

    public String GetQuestion() { return this.questionString; }
    public Boolean IsCorrect() { return this.isCorrect; }
}
