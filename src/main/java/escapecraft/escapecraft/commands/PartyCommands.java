package escapecraft.escapecraft.commands;

import escapecraft.escapecraft.PartyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch(args[0]) {
            case "create": {
                PartyManager.createParty(args[1], sender, args);
            }
        }
        return false;
    }
}
