package tf.ssf.sfort.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.util.sat4j.core.VecInt;
import net.minecraft.command.argument.CoordinateArgument;
import net.minecraft.util.math.Vec2f;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Config implements IMixinConfigPlugin {
    public static Logger LOGGER = LogManager.getLogger();
    public static boolean removedHudName = true;
    public static boolean lessTooltips = false;
    public static boolean dynamicCrosshair = true;
    public static boolean instantCrouch = true;
    public static boolean staticTooltip = false;
    public static boolean removedHudArmor = true;
    @Override
    public void onLoad(String mixinPackage) {
        // Configs
        File confFile = new File(
                FabricLoader.getInstance().getConfigDir().toString(),
                "LessClutter.conf"
        );
        try {
            confFile.createNewFile();
            List<String> la = Files.readAllLines(confFile.toPath());
            List<String> defaultDesc = Arrays.asList(
                    "^-Less item tooltips [false] true | false",
                    "^-Removed hud name display [true] true | false",
                    "^-Dynamic cross-hair [true] true | false",
                    "^-Instant crouch [true] true | false",
                    "^-Static tooltip position [false] true | false",
                    "^-Removed armor hud [true] true | false"
            );
            String[] ls = la.toArray(new String[Math.max(la.size(), defaultDesc.size() * 2)|1]);
            final int hash = Arrays.hashCode(ls);
            for (int i = 0; i<defaultDesc.size();++i)
                ls[i*2+1]= defaultDesc.get(i);

            try{ lessTooltips = ls[0].startsWith("true");}catch (Exception ignore){}
            ls[0] = String.valueOf(lessTooltips);

            try{ removedHudName = ls[2].contains("true");}catch (Exception ignore){}
            ls[2] = String.valueOf(removedHudName);

            try{ dynamicCrosshair = ls[4].contains("true");}catch (Exception ignore){}
            ls[4] = String.valueOf(dynamicCrosshair);

            try{ instantCrouch = ls[6].contains("true");}catch (Exception ignore){}
            ls[6] = String.valueOf(instantCrouch);

            try{ staticTooltip = ls[8].startsWith("true");}catch (Exception ignore){}
            ls[8] = String.valueOf(staticTooltip);

            try{ removedHudArmor = ls[10].contains("true");}catch (Exception ignore){}
            ls[10] = String.valueOf(removedHudArmor);

            if (hash != Arrays.hashCode(ls))
                Files.write(confFile.toPath(), Arrays.asList(ls));
            LOGGER.log(Level.INFO,"tf.ssf.sfort.lessclutter successfully loaded config file");
        } catch(Exception e) {
            LOGGER.log(Level.ERROR,"tf.ssf.sfort.lessclutter failed to load config file, using defaults\n"+e);
        }
    }



    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        switch (mixinClassName){
            case "tf.ssf.sfort.mixin.Cam":{return instantCrouch;}
            case "tf.ssf.sfort.mixin.Item":{return lessTooltips;}
            case "tf.ssf.sfort.mixin.HudItem":{return removedHudName;}
            case "tf.ssf.sfort.mixin.HudArmor":{return removedHudArmor;}
            case "tf.ssf.sfort.mixin.HudCross":{return dynamicCrosshair;}
            case "tf.ssf.sfort.mixin.ItemStatic":{return staticTooltip;}

            default:{return false;}
        }
    }
    @Override public String getRefMapperConfig() { return null; }
    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }
    @Override public List<String> getMixins() { return null; }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
