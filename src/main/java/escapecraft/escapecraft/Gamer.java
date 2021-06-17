package escapecraft.escapecraft;

import java.util.List;

public class Gamer {
    private List<QuestionObject> questionObjects;

    public Gamer(List<QuestionObject> questionObject) {
        this.questionObjects.addAll(questionObject);
    }

    public QuestionObject getQuestionObject(Integer index) { return this.questionObjects.get(index); }
    public Boolean addQuestionObject(QuestionObject answerObject) { return this.questionObjects.add(answerObject); }
    public Boolean addAllQuestionObjects(List<QuestionObject> answerObjects) { return this.questionObjects.addAll(answerObjects); }
    public QuestionObject getQuestion(Integer questionIndex) { return this.questionObjects.get(questionIndex); };
    public String getAnswer(Integer questionIndex, Integer answerIndex) { return this.questionObjects.get(questionIndex).getAnswer(answerIndex).getText(); }
}
