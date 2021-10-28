package tf.ssf.sfort.lessclutter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.BlockItem;
import org.apache.logging.log4j.Level;
import tf.ssf.sfort.lessclutter.mixin.ConfigMixin;

import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;

public class Config implements ModInitializer {
    public static final String dynCrosshairScriptString = "!(~offhand:(block_item);~hand:(block_item))";
    public static Predicate<ClientPlayerEntity> dynamicCrosshair = player ->
            !(player.getMainHandStack().getItem() instanceof BlockItem || player.getOffHandStack().getItem() instanceof BlockItem);

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("fscript") && ConfigMixin.dynamicCrosshair != null)
            dynamicCrosshair = ScriptParsing.parse(ConfigMixin.dynamicCrosshair);
    }

    public static void writeCross(String s) {
        try{
            List<String> la = Files.readAllLines(ConfigMixin.confFile.toPath());
            if(la.size() < 5) return;
            dynamicCrosshair = ScriptParsing.parse(s);
            la.set(4, "script:"+s);
            Files.write(ConfigMixin.confFile.toPath(), la);
        }catch (Exception e){
            ConfigMixin.LOGGER.log(Level.ERROR,"tf.ssf.sfort.lessclutter UI failed to write script\n"+e);
        }
    }

    public static String readCross() {
        try{
            List<String> la = Files.readAllLines(ConfigMixin.confFile.toPath());
            if(la.size() < 5 || !la.get(4).startsWith("script:")) return dynCrosshairScriptString;
            return la.get(4).substring("script:".length());
        }catch (Exception e){
            ConfigMixin.LOGGER.log(Level.ERROR,"tf.ssf.sfort.lessclutter UI failed to read script\n"+e);
        }
        return dynCrosshairScriptString;
    }
}
