package escapecraft.escapecraft;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    private List<QuestionObject> questionObjects = new ArrayList();
    private int questionIndex = 0;

    public Gamer(List<QuestionObject> newQuestionObject) {
        this.questionObjects.addAll(newQuestionObject);
    }

    public Gamer() {

    }

    public int increase() {return this.questionIndex++;}
    public void reset() {this.questionIndex = 0;}
    public Boolean addQuestionObject(QuestionObject answerObject) { return this.questionObjects.add(answerObject); }
    public Boolean addAllQuestionObjects(List<QuestionObject> answerObjects) { return this.questionObjects.addAll(answerObjects); }
    public Boolean setAllQuestions(ArrayList<QuestionObject> questions) {
        this.questionIndex = 0;
        this.questionObjects.clear();
        this.questionObjects.addAll(questions);
        return true;
    }

    public QuestionObject getQuestion() { return this.questionObjects.get(this.questionIndex); }
    public QuestionObject getQuestion(Integer questionIndex) {
        if(questionIndex >= this.questionObjects.size()) return new QuestionObject();
        return this.questionObjects.get(questionIndex);
    };
    public AnswerObject getAnswer(Integer answerIndex) { return this.questionObjects.get(this.questionIndex).getAnswer(answerIndex); }
}
