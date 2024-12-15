package me.suprime.newyearplugin.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.EnumWrappers;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Sulrimka
 */

@UtilityClass
public class ParticleEffect {

    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    public void sendParticlePacket(Player player, @NonNull String particleName, int count,
                                int[] data, boolean longDistance, @NonNull Location center) {

        val packetContainer = protocol.createPacket(PacketType.Play.Server.WORLD_PARTICLES);

        packetContainer.getParticles().write(0, EnumWrappers.Particle.getByName(particleName));

        packetContainer.getFloat().write(0, (float) center.getX());
        packetContainer.getFloat().write(1, (float) center.getY());
        packetContainer.getFloat().write(2, (float) center.getZ());

        packetContainer.getIntegers().write(0, count);

        packetContainer.getBooleans().write(0, longDistance);

        if (data != null) {
            packetContainer.getIntegerArrays().write(0, data);
        }

        try {
            protocol.sendServerPacket(player, packetContainer);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
