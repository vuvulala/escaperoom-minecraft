package escapecraft.escapecraft.commands;

import escapecraft.escapecraft.ChatManager;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Arrays;


public class DisablePlayerSendChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.setDisplayName("Daddy");
        TextComponent message = new TextComponent("Click me daddy uwu");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/say :" + Arrays.toString(args)));

        player.sendMessage(message);
        ChatManager.chatEnabled = false;
        sender.sendMessage("chat disabled");
        return true;
    }
}
