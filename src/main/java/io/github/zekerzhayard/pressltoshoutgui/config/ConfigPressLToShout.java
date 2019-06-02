package io.github.zekerzhayard.pressltoshoutgui.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigPressLToShout {
    private static Configuration config;
    private static List<IConfigElement> configElements = new ArrayList<>();
    private static String category;
    
    public static void setConfig(Configuration config, String category) {
        if (ConfigPressLToShout.config == null) {
            ConfigPressLToShout.config = config;
            ConfigPressLToShout.category = category;
            ConfigPressLToShout.configElements.addAll(new ConfigElement(config.getCategory(category)).getChildElements());
            ConfigPressLToShout.configElements.removeIf(ce -> ce.getName().equals("MESSAGES"));
        }
    }
    
    public static Configuration getConfig() {
        return ConfigPressLToShout.config;
    }
    
    public static List<IConfigElement> getConfigElements() {
        return ConfigPressLToShout.configElements;
    }
    
    public static String getCategory() {
        return ConfigPressLToShout.category;
    }
}
