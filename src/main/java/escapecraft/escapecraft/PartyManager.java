package escapecraft.escapecraft;

import org.bukkit.command.CommandSender;

public class PartyManager {
    public static Party createParty(String partyname, CommandSender admin, String[] members) {
        Party tempParty = new Party(partyname, admin);
        return tempParty;
    }
}
