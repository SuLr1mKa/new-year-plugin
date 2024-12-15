package me.suprime.newyearplugin.snow;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.suprime.newyearplugin.snow.interfaces.ISnow;
import me.suprime.newyearplugin.util.ParticleEffect;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Sulrimka
 */


@ToString
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class SnowManager implements ISnow {

    public final static SnowManager INSTANCE = new SnowManager();

    Map<Player, Boolean> playerSnow = new HashMap<>();

    @Override
    public void setSnowStatus(Player player, boolean status) {
        playerSnow.put(player, status);
    }

    @Override
    public Boolean getSnowStatus(Player player) {
        return playerSnow.computeIfAbsent(player, __ -> true);
    }

    @Override
    public void playSnow(Player player) {
        val random = ThreadLocalRandom.current();
        int RADIUS = 33;

        val world = player.getWorld();
        val location = player.getLocation();

        for (int i = 0; i < 300; i++) {
            val xAdditive = (random.nextFloat() - 0.5f) * RADIUS * 2.0f;
            val max = (float) Math.sqrt(RADIUS * RADIUS - xAdditive * xAdditive) * 2.0f;
            val yAdditive = (random.nextFloat() - 0.5f) * max;
            val zAdditive = (random.nextFloat() - 0.5f) * max;

            int y = location.getBlockY();

            val effectLocation = new Location(world, location.getX() + xAdditive, y + yAdditive, location.getZ() + zAdditive);

            boolean found = false;

            for (int newY = y; newY < y + 20; newY++) {
                val block = world.getBlockAt(
                        effectLocation.getBlockX(),
                        newY,
                        effectLocation.getBlockZ()
                );

                if (block.getType() != Material.AIR) {
                    found = true;
                    break;
                }
            }

            if (found) {
                continue;
            }

            ParticleEffect.sendParticlePacket(player, "fireworksSpark", 2, new int[1], true,
                    effectLocation);
        }
    }

}
