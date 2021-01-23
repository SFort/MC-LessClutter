package tf.ssf.sfort;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Config implements ModInitializer{
    public static Logger LOGGER = LogManager.getLogger();

    public static boolean removedHudName = true;
    public static boolean lessTooltips = false;
    public static boolean dynamicCrosshair = true;
    public static boolean instantCrouch = true;


    @Override
    public void onInitialize() {
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
                    "^-Dynamic cross-hair [true] true | false ",
                    "^-Instant crouch [true] true | false "
            );
            String[] ls = la.toArray(new String[Math.max(la.size(), defaultDesc.size() * 2)|1]);
            for (int i = 0; i<defaultDesc.size();++i)
                ls[i*2+1]= defaultDesc.get(i);

            try{
                lessTooltips = ls[0].contains("true");}catch (Exception ignore){}
            ls[0] = String.valueOf(lessTooltips);

            try{ removedHudName = ls[2].contains("true");}catch (Exception ignore){}
            ls[2] = String.valueOf(removedHudName);

            try{
                dynamicCrosshair = ls[4].contains("true");}catch (Exception ignore){}
            ls[4] = String.valueOf(dynamicCrosshair);
            
            try{
                instantCrouch = ls[6].contains("true");}catch (Exception ignore){}
            ls[6] = String.valueOf(instantCrouch);


            Files.write(confFile.toPath(), Arrays.asList(ls));
            LOGGER.log(Level.INFO,"tf.ssf.sfort.lessclutter successfully loaded config file");
        } catch(Exception e) {
            LOGGER.log(Level.ERROR,"tf.ssf.sfort.lessclutter failed to load config file, using defaults\n"+e);
        }
    }
}