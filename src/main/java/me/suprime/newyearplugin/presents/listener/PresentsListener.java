package me.suprime.newyearplugin.presents.listener;

import lombok.val;
import me.suprime.newyearplugin.presents.PresentsManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Sulrimka
 */

public class PresentsListener implements Listener {

    private final PresentsManager presentsManager = PresentsManager.INSTANCE;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        val player = event.getPlayer();
        val block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (block != null && block.getType() == Material.SKULL) {
                presentsManager.claimPresent(player, block.getLocation());
            }
        }
    }

}
