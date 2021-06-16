package escapecraft.escapecraft;

import java.util.List;

public class Gamer {
    private List<QuestionObject> questionObjects;

    Gamer(List<QuestionObject> questionObjects) {
        this.questionObjects = questionObjects;
    }

    public QuestionObject getQuestionObject(Integer index) { return this.questionObjects.get(index); }
    public Boolean addQuestionObject(QuestionObject questionObject) { return this.questionObjects.add(questionObject); }
    public Boolean addAllQuestionObjects(List<QuestionObject> questionObjects) { return this.questionObjects.addAll(questionObjects); }
    public String getQuestion(Integer index) { return this.questionObjects.get(index).GetQuestion(); }
}
