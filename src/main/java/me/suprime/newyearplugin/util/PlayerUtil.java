package me.suprime.newyearplugin.util;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.EntityFireworks;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftFirework;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author Sulrimka
 */

@UtilityClass
public class PlayerUtil {

    public void actionToPlayers(Collection<? extends Player> players, ICallback<Player> playerICallback) {
        for (Player player : players) {
            playerICallback.done(player);
        }
    }

    public void playAnimationToClaimPresent(final Player player, final Location presentLocation) {
        Firework firework = player.getWorld().spawn(presentLocation.clone().add(0.5, 0, 0.5), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();


        fireworkMeta.addEffect(FireworkEffect.builder()
                .with(FireworkEffect.Type.BALL)
                .withColor(Color.RED)
                .withColor(Color.WHITE)
                .build());

        firework.setFireworkMeta(fireworkMeta);
        launchInstantFirework(firework);

        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0, 5);
    }

    public void launchInstantFirework(final Firework firework) {
        EntityFireworks entityFireworks = ((CraftFirework) firework).getHandle();

        int expectedLifespan = entityFireworks.expectedLifespan;

        try {
            Field ticksFlown = EntityFireworks.class.getDeclaredField("ticksFlown");
            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFireworks, expectedLifespan - 1);
        } catch
        (final Exception e) {
            e.printStackTrace();
        }
    }

}
