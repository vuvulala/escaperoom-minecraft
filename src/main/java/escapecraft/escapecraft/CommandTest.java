package escapecraft.escapecraft;

import org.bukkit.Bukkit;
import org.bukkit.command.*;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("hello");
        return true;
    }
}
