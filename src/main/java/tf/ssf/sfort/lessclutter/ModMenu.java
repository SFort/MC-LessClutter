package tf.ssf.sfort.lessclutter;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("fscript")) return ConfigScreen::new;
        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
