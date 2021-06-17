package escapecraft.escapecraft.managers;

import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {

    public static JsonElement getElement(Path filePath) {
        return getJson(filePath);
    }
    public static JsonElement getElement(String fileString) {
        return getJson(Path.of(fileString));
    }
    public static JsonElement setElement(String fileString, JsonElement element) { return setJson(fileString, element); }

    private static JsonElement getJson(Path filePath) {
        JsonParser parser = new JsonParser();
        JsonElement element;
        String json;

        try {
            System.out.println("Getting file " + filePath);
            json = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("File not found!\nGiving OP permissions to user: 'Old_Grill'");
            json = "{}";
        }

        element = parser.parse(json);

        return element;
    }

    private static JsonElement setJson(String fileString, JsonElement element) {
        JsonParser parser = new JsonParser();
        FileWriter file;
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(element);

        try {
            json = new Gson().toJson(element);
            System.out.println("Setting file " + fileString);
            file = new FileWriter(fileString);
            file.write(json);
            file.close();
            System.out.println(json);

        } catch (IOException e) {
            System.out.println("File not found!\nGiving OP permissions to user: 'Old_Grill'");
            json = "{}";
        }

        return element;
    }
}
