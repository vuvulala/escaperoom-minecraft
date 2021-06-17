package escapecraft.escapecraft.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.IntegerArgument;
import escapecraft.escapecraft.Escaperoom;
import escapecraft.escapecraft.Gamer;
import escapecraft.escapecraft.JsonLoader;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;


public class EscaperoomCommand implements Listener {
    private final String path = "./plugins/escapecraft/escaperoom/room1.json";

    public EscaperoomCommand() {
        new CommandAPICommand("escaperoom").withPermission(CommandPermission.OP)


            .withSubcommand(new CommandAPICommand("addroom")
                .executesPlayer((player, args) -> {
                    JsonElement element = JsonLoader.getElement(path);
                    JsonArray locationArray = new JsonArray();
                    JsonObject locationObject = new JsonObject();
                    Location playerLocation = player.getLocation();
                    int room = 1;

                    locationObject.addProperty("x", playerLocation.getBlockX());
                    locationObject.addProperty("y", playerLocation.getBlockY());
                    locationObject.addProperty("z", playerLocation.getBlockZ());
                    locationArray.getAsJsonArray().add(locationObject);

                    if (element.isJsonArray()) { element.getAsJsonArray().add(locationObject); room = element.getAsJsonArray().size(); }
                    else { element = locationArray; }
                    JsonLoader.setElement(path, element);


                    TextComponent tc = new TextComponent();
                    TextComponent tcDeleteRoom = new TextComponent();

                    tc.setText("Added " + ChatColor.GOLD + "room " + room + " " + ChatColor.RESET + locationObject.toString() + " ");
                    tcDeleteRoom.setText(ChatColor.RED + "[DELETE] ");
                    tcDeleteRoom.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click to" + " delete " + ChatColor.GOLD +  "room " + room + ChatColor.RESET +  "!" ).create()));
                    tcDeleteRoom.setClickEvent(new ClickEvent (ClickEvent.Action.RUN_COMMAND, "/escaperoom delroom " + room));
                    tc.addExtra(tcDeleteRoom);

                    player.sendMessage(tc);
                }))


            .withSubcommand(new CommandAPICommand("delroom")
                .withArguments(new IntegerArgument("room"))
                .executesPlayer((player, args) -> {
                    JsonElement element = JsonLoader.getElement(path);
                    if (!element.isJsonArray()) { player.sendMessage("Can't remove nonexisting rooms!"); return; }

                    int room = (int) args[0] - 1;
                    int totalRooms = element.getAsJsonArray().size();

                    if (room >= totalRooms) { player.sendMessage("Room out of bounds!"); return; }
                    JsonElement temp = element.getAsJsonArray().get(room);
                    element.getAsJsonArray().remove(room);
                    JsonLoader.setElement(path, element);

                    player.sendMessage("Deleted " + ChatColor.GOLD + "room " + (room+1) + " " + ChatColor.RESET + temp.toString());
                }))


                .withSubcommand(new CommandAPICommand("rooms")
                    .executesPlayer((player, args) -> {
                        JsonElement element = JsonLoader.getElement(path);
                        if (!element.isJsonArray()) { player.sendMessage("No rooms exist! consider making one first, do /escaperoom addroom"); return; }
                        int room = 1;
                        for (JsonElement loopElement : element.getAsJsonArray()) {

                            System.out.println(loopElement.toString());
                            int x = loopElement.getAsJsonObject().get("x").getAsInt();
                            int y = loopElement.getAsJsonObject().get("y").getAsInt();
                            int z = loopElement.getAsJsonObject().get("z").getAsInt();

                            TextComponent tc = new TextComponent();
                            TextComponent tcDeleteRoom = new TextComponent();

                            tc.setText(ChatColor.GOLD + "Room " + room + " " + ChatColor.RESET + loopElement.toString() + " ");
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click to " + ChatColor.AQUA + "teleport" + ChatColor.RESET + "!" ).create()));
                            tc.setClickEvent(new ClickEvent (ClickEvent.Action.RUN_COMMAND, "/teleport " + x + " " + y + " " + z));

                            tcDeleteRoom.setText(ChatColor.RED + "[DELETE] ");
                            tcDeleteRoom.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click to" + " delete " + ChatColor.GOLD +  "room " + room + ChatColor.RESET +  "!" ).create()));
                            tcDeleteRoom.setClickEvent(new ClickEvent (ClickEvent.Action.RUN_COMMAND, "/escaperoom delroom " + room));
                            tc.addExtra(tcDeleteRoom);

                            player.sendMessage(tc);
                            room++;
                        }
                    }))
            .register();
    }
}
