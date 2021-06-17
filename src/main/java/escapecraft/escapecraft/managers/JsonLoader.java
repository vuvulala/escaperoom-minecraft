package escapecraft.escapecraft.managers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {

    public static JsonElement getElement(Path filePath) {
        return parser(filePath);
    }
    public static JsonElement getElement(String fileString) {
        return parser(Path.of(fileString));
    }

    private static JsonElement parser(Path filePath) {
        JsonParser parser = new JsonParser();
        JsonElement element;
        String json;

        try {
            json = Files.readString(filePath);
        } catch (IOException e) {
            json = "{}";
        }

        element = parser.parse(json);

        return element;
    }
}
