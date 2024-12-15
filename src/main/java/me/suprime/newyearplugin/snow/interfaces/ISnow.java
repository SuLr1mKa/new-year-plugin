package me.suprime.newyearplugin.snow.interfaces;

import org.bukkit.entity.Player;

public interface ISnow {

    void setSnowStatus(Player player, boolean status);

    Boolean getSnowStatus(Player player);

    void playSnow(Player player);
}
