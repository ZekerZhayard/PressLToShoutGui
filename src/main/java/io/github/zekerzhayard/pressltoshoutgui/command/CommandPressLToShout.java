package io.github.zekerzhayard.pressltoshoutgui.command;

import java.util.List;

import com.google.common.collect.Lists;
import io.github.zekerzhayard.pressltoshoutgui.gui.GuiPressLToShoutConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandPressLToShout extends CommandBase {
    private boolean showGui = false;
    
    public CommandPressLToShout() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    public List<String> getCommandAliases() {
        return Lists.newArrayList("plts");
    }
    
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
    
    @Override
    public String getCommandName() {
        return "pressltoshout";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        this.showGui = true;
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (this.showGui) {
            this.showGui = false;
            Minecraft.getMinecraft().displayGuiScreen(new GuiPressLToShoutConfig(Minecraft.getMinecraft().currentScreen));
        }
    }
}
