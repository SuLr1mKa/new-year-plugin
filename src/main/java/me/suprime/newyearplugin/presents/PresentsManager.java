package me.suprime.newyearplugin.presents;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.suprime.newyearplugin.presents.interfaces.IPresents;
import me.suprime.newyearplugin.util.MessageUtil;
import me.suprime.newyearplugin.util.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sulrimka
 */


@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PresentsManager implements IPresents {

    public final static PresentsManager INSTANCE = new PresentsManager();

    Map<Player, List<Location>> presentsToPlayer = new HashMap<>();

    List<Location> presents = new ArrayList<>();

    @Override
    public void claimPresent(Player player, Location location) {
        if (!isPresentAvailable(location)) {
            return;
        }

        if (hasAlreadyClaimedPresent(player, location)) {
            player.sendMessage(MessageUtil.colorMessageWithPrefix("&cВы уже собрали этот подарок!"));
            return;
        }

        collectPresentForPlayer(player, location);
    }

    private void collectPresentForPlayer(Player player, Location location) {
        val present = getClaimPresentsPlayer(player);

        player.sendMessage(MessageUtil.colorMessageWithPrefix("&eВы собрали подарок!"));

        present.add(location);
        presentsToPlayer.put(player, present);

        PlayerUtil.playAnimationToClaimPresent(player, location);
    }

    @Override
    public boolean hasAlreadyClaimedPresent(Player player, Location location) {
        return getClaimPresentsPlayer(player).contains(location);
    }

    @Override
    public List<Location> getClaimPresentsPlayer(Player player) {
        return presentsToPlayer.computeIfAbsent(player, __ -> new ArrayList<>());
    }

    @Override
    public boolean isPresentAvailable(Location location) {
        return getPresents().contains(location);
    }

    @Override
    public void loadPresents(List<Location> list) {
        presents.addAll(list);
    }
}
