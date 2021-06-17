package escapecraft.escapecraft;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

            System.out.println(questionTitle);
            JsonArray JSONAnswers = JSONQuestion.getAsJsonArray("answers");

            QuestionObject tmpQuestion = new QuestionObject(questionTitle);

            for(JsonElement tmp1 : JSONAnswers) {
                JsonObject JSONAnswer = tmp1.getAsJsonObject();

                tmpQuestion.addAnswer(new AnswerObject(JSONAnswer.get("answer").getAsString(), JSONAnswer.get("correct").getAsBoolean()));
                System.out.print("answer: ");
                System.out.print(JSONAnswer.get("answer").getAsString());
                System.out.print(" | correct: ");
                System.out.println(JSONAnswer.get("correct").getAsBoolean());
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
            System.out.println("Getting file " + "./plugins/escapecraft/quiz/" + questionName + ".json");
            json = Files.readString(Path.of("./plugins/escapecraft/quiz/" + questionName + ".json"));
        } catch (IOException e) {
            System.out.println("File not found!");
            json = "{}";
        }

        object = parser.parse(json).getAsJsonObject();

        return object;
    }

    public static boolean quizExists(String questionName) {
        try {
            System.out.println("Checking file " + "./plugins/escapecraft/quiz/" + questionName + ".json");
            Files.readString(Path.of("./plugins/escapecraft/quiz/" + questionName + ".json"));
            return true;
        } catch (IOException e) {
            System.out.println("File doesn't exist!");
            return false;
        }
    }

    private static ArrayList<QuestionObject> getEmptyQuizArray() {
        ArrayList<QuestionObject> questions = new ArrayList();
        return questions;
    }
}
