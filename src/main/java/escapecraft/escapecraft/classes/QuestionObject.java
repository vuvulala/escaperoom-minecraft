package escapecraft.escapecraft.classes;

import java.util.ArrayList;

public class QuestionObject {
    private String title;

    private ArrayList<AnswerObject> answers = new ArrayList(4);
    public QuestionObject(ArrayList<AnswerObject> newAnswers, String title) {
        answers.addAll(newAnswers);
        this.title = title;
    }

    public QuestionObject(String title) {
        this.title = title;
    }

    public QuestionObject(ArrayList<AnswerObject> newAnswers) {
        this.title = "UNDEFINED TITLE";
        answers.addAll(newAnswers);
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

    public ArrayList<AnswerObject> getAnswers() {
        return answers;
    }

    public String getTitle() {
        return this.title;
    }
}
