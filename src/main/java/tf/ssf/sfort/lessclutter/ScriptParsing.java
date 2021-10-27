package tf.ssf.sfort.lessclutter;

import net.minecraft.client.network.ClientPlayerEntity;
import tf.ssf.sfort.script.ScriptParser;
import tf.ssf.sfort.script.instance.PlayerEntityScript;

import java.util.function.Predicate;

public class ScriptParsing {
    public static Predicate<ClientPlayerEntity> parse(String str){
        return new ScriptParser<ClientPlayerEntity>(new PlayerEntityScript<>()).parse(str);
    }
}
