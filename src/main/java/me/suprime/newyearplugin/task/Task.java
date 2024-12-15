package me.suprime.newyearplugin.task;

import lombok.val;
import me.suprime.newyearplugin.NewYearPlugin;
import me.suprime.newyearplugin.presents.PresentsManager;
import me.suprime.newyearplugin.snow.SnowManager;
import me.suprime.newyearplugin.util.ParticleEffect;
import me.suprime.newyearplugin.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author Sulrimka
 */


public class Task implements Runnable {

    private int taskId = 0;

    @Override
    public void run() {
        PlayerUtil.actionToPlayers(Bukkit.getOnlinePlayers(),
                (players -> {
                    val snow = SnowManager.INSTANCE;
                    val presentsManager = PresentsManager.INSTANCE;

                    for (Location location : presentsManager.getPresents()) {
                        if (presentsManager.getClaimPresentsPlayer(players).contains(location)) {
                            continue;
                        }

                        ParticleEffect.sendParticlePacket(players, "heart", 1, new int[1], true,
                                location.clone().add(0.5, 0.25, 0.5));
                    }

                    if (!snow.getSnowStatus(players)) {
                        return;
                    }

                    snow.playSnow(players);
                }));
    }

    public void start() {
        if (taskId == 0) {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(NewYearPlugin.getInstance(), this, 5, 5);
        }
    }
}
