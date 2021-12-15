package tf.ssf.sfort.lessclutter;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import tf.ssf.sfort.script.Default;
import tf.ssf.sfort.script.ScriptParser;

import java.util.function.Predicate;

public class ScriptParsing {
    public static Predicate<ClientPlayerEntity> parse(String str){
        Predicate<PlayerEntity> ret = new ScriptParser<>(Default.PLAYER_ENTITY).parse(str);
        if (ret != null) return ret::test;
        return null;
    }
}
