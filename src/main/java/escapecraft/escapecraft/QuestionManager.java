package escapecraft.escapecraft;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QuestionManager {
    public static JsonObject getQuestion(String questionName) {
        JsonParser parser = new JsonParser();
        JsonObject object;
        String json = "{\n" +
                "  'questions':\n" +
                "  [\n" +
                "    {\n" +
                "      'question': 'I hvilket år ble Kasper født?',\n" +
                "      'answers': \n" +
                "      [\n" +
                "        {\n" +
                "          'answer': '2003',\n" +
                "          'correct': 'true'\n" +
                "        },\n" +
                "        {\n" +
                "          'answer': '2004',\n" +
                "          'correct': 'false'\n" +
                "        },\n" +
                "        {\n" +
                "          'answer': '2005',\n" +
                "          'correct': 'false'\n" +
                "        },\n" +
                "        {\n" +
                "          'answer': '2006',\n" +
                "          'correct': 'false'\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        object = parser.parse(json).getAsJsonObject();
        return object;
    }

    @Deprecated
    public static org.json.simple.JSONObject getQuestionSimple(String questionName) {
        String question = "{\n" +
                "  \"question 1\":{\n" +
                "    \"title\": \"Dette er et spørsmål\",\n" +
                "    \"answers\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"shortDesc\":\"svar 1\",\n" +
                "        \"longDesc\":\"svar 1 er det rette svaret\",\n" +
                "        \"correctAnswer\":true\n" +
                "      },\n" +
                "      {\n" +
                "        \"shortDesc\":\"svar 2\",\n" +
                "        \"longDesc\":\"svar 2 er enda ett rett svar\",\n" +
                "        \"correctAnswer\":true\n" +
                "      },\n" +
                "      {\n" +
                "        \"shortDesc\":\"svar 3\",\n" +
                "        \"longDesc\":\"svar 3 er feil\",\n" +
                "        \"correctAnswer\":false\n" +
                "      },\n" +
                "      {\n" +
                "        \"shortDesc\":\"svaadsa\",\n" +
                "        \"longDesc\":\"denne er feil\",\n" +
                "        \"correctAnswer\":false\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        return new org.json.simple.JSONObject();
//        JSONParser parser = new JSONParser();
//        try {
//            Object json = parser.parse(question);
//            org.json.simple.JSONObject obj = (org.json.simple.JSONObject) json;
//            return obj;
//        } catch (Exception e) {
//            return new org.json.simple.JSONObject();
//        }

    }

}
