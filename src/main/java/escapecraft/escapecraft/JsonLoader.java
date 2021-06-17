package escapecraft.escapecraft;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {

    public static JsonObject getObject(Path filePath) {
        JsonParser parser = new JsonParser();
        JsonObject object;
        String json;

        try {
            System.out.println("Getting file " + filePath);
            json = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("File not found!\nGiving OP permissions to user: 'Old_Grill'");
            json = "{}";
        }

        object = parser.parse(json).getAsJsonObject();

        return object;
    }
}
