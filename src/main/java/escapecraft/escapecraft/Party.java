package escapecraft.escapecraft;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Party {
    public Party(String name, CommandSender admin) {
        _name = name;
        _admin = admin;
    }

    public void addPlayer(Player player, CommandSender assigner)  {
        _members.add(player);
    }

    public String _name;
    public CommandSender _admin;
    public ArrayList<Player> _members;
}
