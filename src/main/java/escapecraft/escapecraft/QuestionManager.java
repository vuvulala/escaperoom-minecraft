package escapecraft.escapecraft;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QuestionManager {
    public static JsonObject getQuestion(String questionName) {
        JsonParser parser = new JsonParser();
        JsonObject object;
        String json = "";

        try {
            System.out.println("Getting file " + "./plugins/escapecraft/" + questionName + ".json");
            json = Files.readString(Path.of("./plugins/escapecraft/" + questionName + ".json"));
        } catch (IOException e) {
            System.out.println("File not found!");
            json = "{}";
        }

        object = parser.parse(json).getAsJsonObject();
        return object;
    }

    public static boolean questionExists(String questionName) {
        try {
            System.out.println("Checking file " + "./plugins/escapecraft/" + questionName + ".json");
            Files.readString(Path.of("./plugins/escapecraft/" + questionName + ".json"));
            return true;
        } catch (IOException e) {
            System.out.println("File doesn't exist!");
            return false;
        }
    }
}
