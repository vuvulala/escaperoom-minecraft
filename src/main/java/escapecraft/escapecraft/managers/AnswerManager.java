package escapecraft.escapecraft.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class AnswerManager {
    public static TextComponent getAnswers(JsonObject quiz, int questionNumber, String partyName) {
        String[] answerIdentifiers = {org.bukkit.ChatColor.RED + "△ ", org.bukkit.ChatColor.BLUE + "◇ ", org.bukkit.ChatColor.YELLOW + "○ ", org.bukkit.ChatColor.GREEN + "□ "};

        TextComponent title = new TextComponent();

        JsonArray questionsArray = quiz.getAsJsonArray("questions");

        JsonObject questionObject = questionsArray.get(questionNumber).getAsJsonObject();
        String questionTitle = questionObject.get("question").getAsString();

        title.setText(questionTitle);
        title.setBold(true);
        title.setColor(ChatColor.GOLD);

        JsonArray answersArray = questionObject.getAsJsonArray("answers");

        for (int i = 0; i < answersArray.size(); i++) {
            //Get the identifier to use, with a default value of the answer index
            String currentIdentifier = Integer.toString(i);
            if(i < answerIdentifiers.length) currentIdentifier = answerIdentifiers[i];

            //gets the answer description and if the answer is correct
            JsonElement answer = answersArray.get(i).getAsJsonObject().get("answer");
            JsonElement isCorrect = answersArray.get(i).getAsJsonObject().get("correct");

            //sends the answer and assigns events to the answer
            TextComponent message = new TextComponent(currentIdentifier + answer.getAsString());
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer " + partyName + " " + questionNumber + " " + i + " " + isCorrect.getAsString()));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Svar: " + currentIdentifier)));
            title.addExtra("\n");
            title.addExtra(message);
        }

        return title;
    }
}
