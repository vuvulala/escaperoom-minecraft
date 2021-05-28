package escapecraft.escapecraft;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PartyManager {
    public static Map<String, Party> parties;
    static {
        parties = new HashMap<String, Party>();
    }


    public static Party createParty(String partyname, CommandSender admin, Collection<Player> members) {
        Party tempParty = new Party(partyname, admin);
        parties.put(partyname, tempParty);
        return tempParty;
    }

    public static boolean has(String partyName) {
        if(parties.isEmpty()) return false;
        return parties.containsKey(partyName);
    }

    public static void disband(String partyName) {
        parties.remove(partyName);
    }

    public static String[] getAllAsString() {
        return parties.keySet().toArray(new String[0]);
    }
}
