package me.suprime.newyearplugin.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

/**
 * @author Sulrimka
 */

@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageUtil {

    String prefix = "&c&lNEW&f&lYEAR &8|";

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String colorMessageWithPrefix(String message) {
        return color(String.format("%s " + message, prefix));
    }
}
