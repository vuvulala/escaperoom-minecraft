package escapecraft.escapecraft;

import java.util.ArrayList;

public class QuestionObject {
    private ArrayList<AnswerObject> answers = new ArrayList<AnswerObject>(4);
    public QuestionObject(ArrayList<AnswerObject> newAnswers) {
        answers.addAll(newAnswers);
    }

    public QuestionObject() {

    }

    public void addAnswer(AnswerObject newAnswer) {
        answers.add(newAnswer);
    }

    public AnswerObject getAnswer(int index) {
        return answers.get(index);
    }
}
