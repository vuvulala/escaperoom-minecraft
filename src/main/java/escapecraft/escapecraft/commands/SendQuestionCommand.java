package escapecraft.escapecraft.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import escapecraft.escapecraft.QuestionManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class SendQuestionCommand implements CommandExecutor {
    String[] answerIdentifier = {ChatColor.RED + "△ ", ChatColor.BLUE + "◇ ", ChatColor.YELLOW + "○ ", ChatColor.GREEN + "□ "};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        JsonObject questionObject = QuestionManager.getQuestion("");
        JsonArray questionsArray = questionObject.getAsJsonArray("questions");

        sender.sendMessage(StringUtils.repeat(" \n", 3));

        for (JsonElement questionElement : questionsArray) {
            JsonElement question = questionElement.getAsJsonObject().get("question");
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + question.getAsString());

            JsonArray answersArray = questionElement.getAsJsonObject().getAsJsonArray("answers");
            generateAnswers(answersArray, sender);

        }
        return true;
    }

    public void generateAnswers(JsonArray answersArray, CommandSender sender) {

        for (int i = 0; i<answersArray.size(); i++) {
            JsonElement answer = answersArray.get(i).getAsJsonObject().get("answer");
            JsonElement correct = answersArray.get(i).getAsJsonObject().get("correct");
            TextComponent message = new TextComponent(answerIdentifier[i] + answer.getAsString());
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, correct.getAsString()));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Svar: " + answerIdentifier[i])));
            sender.sendMessage(message);
        }

        return;
    }
}