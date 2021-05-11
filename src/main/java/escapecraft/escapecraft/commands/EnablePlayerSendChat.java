package escapecraft.escapecraft.commands;

import escapecraft.escapecraft.ChatManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnablePlayerSendChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatManager.chatEnabled = true;
        sender.sendMessage("chat enabled");
        return true;
    }
}
