package escapecraft.escapecraft;

import java.util.ArrayList;

public class QuestionObject {
    private String title;

    private ArrayList<AnswerObject> answers = new ArrayList<AnswerObject>(4);
    public QuestionObject(ArrayList<AnswerObject> newAnswers, String title) {
        answers.addAll(newAnswers);
        this.title = title;
    }

    public QuestionObject(String title) {
        this.title = title;
    }

    public QuestionObject() {
        this.title = "UNDEFINED TITLE";
    }

    public void addAnswer(AnswerObject newAnswer) {
        answers.add(newAnswer);
    }

    public AnswerObject getAnswer(int index) {
        if(index >= answers.size()) return new AnswerObject("...scrub", false);
        return answers.get(index);
    }

    public String getTitle() {
        return this.title;
    }
}
