package escapecraft.escapecraft;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

public class QuestionManager {
    public static JSONObject getQuestion(String questionName) {
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


        JSONParser parser = new JSONParser();
        try {
            Object json = parser.parse(question);
            JSONObject obj = (JSONObject) json;
            return obj;
        } catch(Exception e) {return new JSONObject();}

    }
}
