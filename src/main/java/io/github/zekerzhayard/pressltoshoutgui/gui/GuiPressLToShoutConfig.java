package io.github.zekerzhayard.pressltoshoutgui.gui;

import games.strafekits.pressltoshout.PressLToShout;
import games.strafekits.pressltoshout.common.ConfigLoader;
import io.github.zekerzhayard.pressltoshoutgui.config.ConfigPressLToShout;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfig;
import org.apache.commons.lang3.StringUtils;

public class GuiPressLToShoutConfig extends GuiConfig {
    public GuiPressLToShoutConfig(GuiScreen parent) {
        super(parent, ConfigPressLToShout.getConfigElements(), PressLToShout.MODID, false, false, PressLToShout.NAME);
    }
    
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        String message = StringUtils.join(ConfigPressLToShout.getConfig().get(ConfigPressLToShout.getCategory(), "MessageList", new String[0], "").getStringList(), ";");
        ConfigLoader.Messages = new Property("MESSAGES", message, Property.Type.STRING);
        ConfigPressLToShout.getConfig().save();
    }
}
