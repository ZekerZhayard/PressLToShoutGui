import io.github.zekerzhayard.pressltoshoutgui.config.ConfigPressLToShout;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.lang3.StringUtils;

public class PressLToShoutHook {
    public static Property setConfig(Configuration config, String category, String key, String defaultValue, String comment) {
        ConfigPressLToShout.setConfig(config, category.toLowerCase());
        return new Property(key, StringUtils.join(config.get(category, "MessageList", defaultValue.split(";"), comment).getStringList(), ";"), Property.Type.STRING);
    }
}
