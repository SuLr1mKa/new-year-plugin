package me.suprime.newyearplugin.presents.interfaces;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.List;

public interface IPresents {

    void claimPresent(Player player, Location location);

    List<Location> getClaimPresentsPlayer(Player player);

    List<Location> getPresents();

    boolean isPresentAvailable(Location location);

    boolean hasAlreadyClaimedPresent(Player player, Location location);

    void loadPresents(List<Location> list);
}
