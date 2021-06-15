package escapecraft.escapecraft.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import escapecraft.escapecraft.managers.QuestionManager;
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
    String[] answerIdentifiers = {ChatColor.RED + "△ ", ChatColor.BLUE + "◇ ", ChatColor.YELLOW + "○ ", ChatColor.GREEN + "□ "};

    /**
     * main command body, starts a quiz from a JSON file
     * @param sender the sender of the command
     * @param command the name of the command invoked
     * @param label the internal name of the command
     * @param args the arguments of the command after the command name
     * @return returns true if command is successful
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Sets currentQuizName to have a default of "", otherwise it will be the first argument
        String currentQuizName = args.length > 0 ? args[0] : "";

        //if question is non-existent, quit the command and send a message to the sender
        if(!QuestionManager.questionExists(currentQuizName)) {
            sender.sendMessage("That question doesn't exist!");
            return true;
        };

        //gets the question form question manager
        JsonObject questionObject = QuestionManager.getQuestion(currentQuizName);
        JsonArray questionsArray = questionObject.getAsJsonArray("questions");

        //spaces out the quiz
        sender.sendMessage(StringUtils.repeat(" \n", 3));

        //sends the questions
        for (JsonElement questionElement : questionsArray) {
            JsonElement question = questionElement.getAsJsonObject().get("question");
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + question.getAsString());

            JsonArray answersArray = questionElement.getAsJsonObject().getAsJsonArray("answers");
            generateAnswers(answersArray, sender);

        }
        return true;
    }

    /**
     * Creates a set of choices from an array of answers
     *
     * @param answersArray The JSON array to generate choices from
     * @param sender The sender to send the choices to
     */
    public void generateAnswers(JsonArray answersArray, CommandSender sender) {

        for (int i = 0; i < answersArray.size(); i++) {
            //Get the identifier to use, with a default value of the answer index
            String currentIdentifier = Integer.toString(i);
            if(i < answerIdentifiers.length) currentIdentifier = answerIdentifiers[i];

            //gets the answer description and if the answer is correct
            JsonElement answer = answersArray.get(i).getAsJsonObject().get("answer");
            JsonElement isCorrect = answersArray.get(i).getAsJsonObject().get("correct");

            //sends the answer and assigns events to the answer
            TextComponent message = new TextComponent(currentIdentifier + answer.getAsString());
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, isCorrect.getAsString()));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Svar: " + currentIdentifier)));

            //sends the answer to the person who sent the command
            sender.sendMessage(message);
        }

        return;
    }
}