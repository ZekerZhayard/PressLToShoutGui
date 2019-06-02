package io.github.zekerzhayard.pressltoshoutgui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.github.zekerzhayard.pressltoshoutgui.command.CommandPressLToShout;
import io.github.zekerzhayard.pressltoshoutgui.gui.GuiPressLToShoutFactory;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.StringUtils;

public class PressLToShoutGui extends DummyModContainer {
    public final static String MODID = "pressltoshoutgui";
    public final static String NAME = "PressLToShoutGui";
    private boolean enabled = true;
    
    public PressLToShoutGui() {
        super(new ModMetadata());
        ModMetadata md = this.getMetadata();
        md.modId = PressLToShoutGui.MODID;
        md.name = PressLToShoutGui.NAME;
        md.description = "Add a command and a setting gui to PressLToShout.";
        md.url = "https://github.com/ZekerZhayard/PressLToShoutGui";
        md.updateJSON = "https://raw.githubusercontent.com/ZekerZhayard/PressLToShoutGui/master/update.json";
        md.version = "@VERSION@";
        md.authorList.add("ZekerZhayard");
        md.credits = "GPLv3";
        md.useDependencyInformation = true;
        Loader.instance().computeDependencies(this.getSortingRules(), md.requiredMods, md.dependencies, md.dependants);
    }
    
    @Override
    public List<ArtifactVersion> getDependants() {
        return this.getMetadata().dependants;
    }
    
    @Override
    public List<ArtifactVersion> getDependencies() {
        return this.getMetadata().dependencies;
    }
    
    @Override
    public Set<ArtifactVersion> getRequirements() {
        return this.getMetadata().requiredMods;
    }
    
    @Override
    public Object getMod() {
        return this;
    }
    
    @Override
    public String getSortingRules() {
        return "required-after:pressltoshout;";
    }
    
    @Override
    public File getSource() {
        return new File(StringUtils.substringBeforeLast(PressLToShoutGui.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "!"));
    }
    
    @Override
    public boolean matches(Object mod) {
        return this.equals(mod);
    }
    
    @Override
    public void setEnabledState(boolean enabled) {
        this.enabled = enabled;
    }
    
    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        if (this.enabled) {
            bus.register(this);
        }
        return this.enabled;
    }
    
    @Override
    public VersionRange acceptableMinecraftVersionRange() {
        return VersionParser.parseRange("[1.8.9]");
    }
    
    @Override
    public Map<String, String> getSharedModDescriptor() {
        Map<String, String> descriptor = Maps.newHashMap();
        descriptor.put("modsystem", "FML");
        descriptor.put("id", this.getModId());
        descriptor.put("version", this.getDisplayVersion());
        descriptor.put("name", this.getName());
        descriptor.put("url", this.getMetadata().url);
        descriptor.put("authors", this.getMetadata().getAuthorList());
        descriptor.put("description", this.getMetadata().description);
        return descriptor;
    }
    
    @Override
    public String getGuiClassName() {
        return GuiPressLToShoutFactory.class.getName();
    }
    
    @Override
    public boolean shouldLoadInEnvironment() {
        return FMLCommonHandler.instance().getSide().equals(Side.CLIENT);
    }
    
    @Override
    public URL getUpdateUrl() {
        try {
            return new URL(this.getMetadata().updateJSON);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Subscribe
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandPressLToShout());
    }
}
