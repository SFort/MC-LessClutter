package tf.ssf.sfort.lessclutter.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ConfigMixin implements IMixinConfigPlugin {
    private static final String mixin_dir = "tf.ssf.sfort.lessclutter.mixin";
    public static Logger LOGGER = LogManager.getLogger();
    public static boolean removedHudName = true;
    public static Boolean lessTooltips = null;
    public static String dynamicCrosshair = null;
    public static boolean dynamicCrosshairMixin = true;
    public static boolean instantCrouch = true;
    public static boolean staticTooltip = false;
    public static int hideflags = 2;
    public static Boolean removedHudArmor = false;
    public static Boolean removedBook = null;
    public static Float Vignette = null;
    public static File confFile = new File(
            FabricLoader.getInstance().getConfigDir().toFile(),
            "LessClutter.conf"
    );
    @Override
    public void onLoad(String mixinPackage) {
        // Configs
        try {
            //confFile.createNewFile();
            List<String> la = Files.readAllLines(confFile.toPath());
            List<String> defaultDesc = Arrays.asList(
                    "^-Less item tooltips [false] true | false | enchants",
                    "^-Removed hud name display [true] true | false",
                    "^-Dynamic cross-hair [true] true | false // another option is 'script:' if fscript lib is installed",
                    "^-Instant crouch [true] true | false",
                    "^-Static tooltip position [false] true | false",
                    "^-Removed armor hud [true] true | false | purge",
                    "^-Vignette [0.0] null | 0.0 - 1.0 //setting to null disables the feature",
                    "^-Remove Recipe Book [false] true | false | purge",
                    "^-Tooltip add hide flags [2] //https://notwoods.github.io/minecraft-tools/hideflags/"
            );
            String[] ls = la.toArray(new String[Math.max(la.size(), defaultDesc.size() * 2)|1]);
            final int hash = Arrays.hashCode(ls);
            for (int i = 0; i<defaultDesc.size();++i)
                ls[i*2+1]= defaultDesc.get(i);

            try{ lessTooltips = ls[0].startsWith("false")? null : ls[0].contains("enchants");}catch (Exception ignore){}
            ls[0] = lessTooltips == null ? "false" : lessTooltips ? "enchants" : "true";

            try{ removedHudName = ls[2].contains("true");}catch (Exception ignore){}
            ls[2] = String.valueOf(removedHudName);

            try{
                if (ls[4].startsWith("script:")){
                    dynamicCrosshair = ls[4].substring("script:".length());
                }else if (ls[4].startsWith("false")){
                   dynamicCrosshairMixin = false;
                   ls[4] = String.valueOf(dynamicCrosshairMixin);
                }else{
                    ls[4] = String.valueOf(dynamicCrosshairMixin);
                }
            }catch (Exception ignore){}

            try{ instantCrouch = ls[6].contains("true");}catch (Exception ignore){}
            ls[6] = String.valueOf(instantCrouch);

            try{ staticTooltip = ls[8].startsWith("true");}catch (Exception ignore){}
            ls[8] = String.valueOf(staticTooltip);

            try{ removedHudArmor = ls[10].startsWith("false")? null : ls[10].contains("purge");}catch (Exception ignore){}
            ls[10] = removedHudArmor == null ? "false" : removedHudArmor ? "purge" : "true";

            try{ Vignette = Float.parseFloat(ls[12]);}catch (Exception ignore){}
            ls[12] = Vignette == null ? "null" : String.valueOf(Vignette);

            try{ removedBook = ls[14].startsWith("false")? null : ls[14].contains("purge");}catch (Exception ignore){}
            ls[14] = removedBook == null ? "false" : removedBook ? "purge" : "true";

            try{ hideflags = Integer.parseInt(ls[16]);}catch (Exception ignore){}
            ls[16] = String.valueOf(hideflags);

            if (hash != Arrays.hashCode(ls))
                Files.write(confFile.toPath(), Arrays.asList(ls));
            LOGGER.log(Level.INFO,"tf.ssf.sfort.lessclutter successfully loaded config file");
        } catch(Exception e) {
            LOGGER.log(Level.ERROR,"tf.ssf.sfort.lessclutter failed to load config file, using defaults\n"+e);
        }
    }



    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return switch (mixinClassName) {
            case mixin_dir + ".Cam" -> instantCrouch;
            case mixin_dir + ".Item" -> lessTooltips != null && !lessTooltips;
            case mixin_dir + ".ItemEnchant" -> lessTooltips != null && lessTooltips;
            case mixin_dir + ".ItemFlags" -> hideflags != 0;
            case mixin_dir + ".HudItem" -> removedHudName;
            case mixin_dir + ".HudArmor" -> removedHudArmor != null && !removedHudArmor;
            case mixin_dir + ".HudArmorDirty" -> removedHudArmor != null && removedHudArmor;
            case mixin_dir + ".HudVignotRed" -> Vignette != null && Vignette != 0.0F;
            case mixin_dir + ".HudVignot" -> Vignette != null && Vignette == 0.0F;
            case mixin_dir + ".access.Scren", mixin_dir + ".access.TextButton", mixin_dir + ".InvBook" -> removedBook != null;
            case mixin_dir + ".NullBook" -> removedBook != null && removedBook;
            case mixin_dir + ".HudCross" -> dynamicCrosshairMixin;
            case mixin_dir + ".ItemStatic" -> staticTooltip;
            default -> true;
        };
    }
    @Override public String getRefMapperConfig() { return null; }
    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }
    @Override public List<String> getMixins() { return null; }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
