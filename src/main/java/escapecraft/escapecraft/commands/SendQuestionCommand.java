package escapecraft.escapecraft.commands;
import escapecraft.escapecraft.ChatManager;
import escapecraft.escapecraft.QuestionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;

public class SendQuestionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        JSONObject question = QuestionManager.getQuestion("peepee");
        String asd = (String)question.get("question 1");
        sender.sendMessage(asd);
        sender.sendMessage();
        return true;
    }
}