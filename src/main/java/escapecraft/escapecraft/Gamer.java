package escapecraft.escapecraft;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class Gamer implements Player {

    public Gamer() {

    }

    public Integer questionNumber;
    public String quiz;
}
