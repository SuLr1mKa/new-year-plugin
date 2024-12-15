package me.suprime.newyearplugin.util;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author Sulrimka
 */
@UtilityClass
public class LocationUtil {

    public Location stringLocation(String locationString) {

        val locationData = locationString.split("; ");
        val world = Bukkit.getWorld(locationData[0]);

        val location = new Location(world, Double.parseDouble(locationData[1]), Double.parseDouble(locationData[2]), Double.parseDouble(locationData[3]));

        if (locationData.length > 4) {
            location.setYaw(Float.parseFloat(locationData[4]));
            location.setPitch(Float.parseFloat(locationData[5]));
        }

        return location;
    }
}
