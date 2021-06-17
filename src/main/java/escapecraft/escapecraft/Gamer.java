package escapecraft.escapecraft;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    private List<QuestionObject> questionObjects = new ArrayList();
    private int questionIndex = 0;

    public Gamer(List<QuestionObject> newQuestionObject) {
        this.questionObjects.addAll(newQuestionObject);
    }

    public int increase() {return this.questionIndex++;}
    public QuestionObject getQuestionObject(Integer index) { return this.questionObjects.get(index); }
    public Boolean addQuestionObject(QuestionObject answerObject) { return this.questionObjects.add(answerObject); }
    public Boolean addAllQuestionObjects(List<QuestionObject> answerObjects) { return this.questionObjects.addAll(answerObjects); }
    public QuestionObject getQuestion() { return this.questionObjects.get(this.questionIndex); }
    public QuestionObject getQuestion(Integer questionIndex) {
        if(questionIndex >= this.questionObjects.size()) return new QuestionObject();
        return this.questionObjects.get(questionIndex);
    };
    public AnswerObject getAnswer(Integer answerIndex) { return this.questionObjects.get(this.questionIndex).getAnswer(answerIndex); }
}
