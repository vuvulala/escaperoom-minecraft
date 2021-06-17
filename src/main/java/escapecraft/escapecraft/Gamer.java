package escapecraft.escapecraft;

import java.util.List;

public class Gamer {
    private List<AnswerObject> answerObject;

    Gamer(List<AnswerObject> answerObject) {
        this.answerObject = answerObject;
    }

    public AnswerObject getQuestionObject(Integer index) { return this.answerObject.get(index); }
    public Boolean addQuestionObject(AnswerObject answerObject) { return this.answerObject.add(answerObject); }
    public Boolean addAllQuestionObjects(List<AnswerObject> answerObjects) { return this.answerObject.addAll(answerObjects); }
    public String getQuestion(Integer index) { return this.answerObject.get(index).GetQuestion(); }
}
