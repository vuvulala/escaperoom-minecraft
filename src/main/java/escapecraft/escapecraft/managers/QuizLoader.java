package escapecraft.escapecraft.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import escapecraft.escapecraft.classes.AnswerObject;
import escapecraft.escapecraft.classes.QuestionObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class QuizLoader {
    public static ArrayList<QuestionObject> getQuiz(String questionName) {
        if(!quizExists(questionName)) {
            ArrayList<QuestionObject> tmp =  getEmptyQuizArray();
            tmp.add(new QuestionObject());
            return tmp;
        }

        JsonObject object = getObject(questionName);
        if(object.getAsJsonArray("questions") == null) return getEmptyQuizArray();

        ArrayList<QuestionObject> questions = getEmptyQuizArray();

        JsonArray JSONQuestions = object.getAsJsonArray("questions");


        for(JsonElement tmp : JSONQuestions) {
            JsonObject JSONQuestion = tmp.getAsJsonObject();

            String questionTitle = JSONQuestion.get("question").getAsString();

            JsonArray JSONAnswers = JSONQuestion.getAsJsonArray("answers");

            QuestionObject tmpQuestion = new QuestionObject(questionTitle);

            for(JsonElement tmp1 : JSONAnswers) {
                JsonObject JSONAnswer = tmp1.getAsJsonObject();

                tmpQuestion.addAnswer(new AnswerObject(JSONAnswer.get("answer").getAsString(), JSONAnswer.get("correct").getAsBoolean()));
            }

            questions.add(tmpQuestion);
        }

        return questions;
    }

    private static JsonObject getObject(String questionName) {
        JsonParser parser = new JsonParser();
        JsonObject object;
        String json;

        try {
            json = Files.readString(Path.of("./plugins/escapecraft/quiz/" + questionName + ".json"));
        } catch (IOException e) {
            json = "{}";
        }

        object = parser.parse(json).getAsJsonObject();

        return object;
    }

    public static boolean quizExists(String questionName) {
        try {
            Files.readString(Path.of("./plugins/escapecraft/quiz/" + questionName + ".json"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static ArrayList<QuestionObject> getEmptyQuizArray() {
        return new ArrayList<>();
    }
}
