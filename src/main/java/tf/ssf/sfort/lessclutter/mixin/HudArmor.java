package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = InGameHud.class, priority = 3702)
public abstract class HudArmor {
	@ModifyVariable(method = "renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", index = 19, name = "u", at = @At("STORE"))
	private int armor(int old) {
		return 0;
	}
}
