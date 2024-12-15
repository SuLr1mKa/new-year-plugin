package me.suprime.newyearplugin.snow.command;

import lombok.val;
import me.suprime.newyearplugin.snow.SnowManager;
import me.suprime.newyearplugin.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Sulrimka
 */


public class SnowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        val snow = SnowManager.INSTANCE;
        val player = (Player) commandSender;

        val toStatus = !snow.getSnowStatus(player);

        snow.setSnowStatus(player, toStatus);
        player.sendMessage(MessageUtil.colorMessageWithPrefix("&fВы " + (!toStatus ? "&cвыключили" : "&aвключили") +  " &fснег в лобби!"));

        return false;

    }
}
