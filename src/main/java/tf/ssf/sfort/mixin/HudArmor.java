package tf.ssf.sfort.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = InGameHud.class, priority = 3122)
public abstract class HudArmor {
	@ModifyVariable(method = "renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V", name = "v", at = @At("STORE"))
	private int armor(int old) {
		return 0;
	}
}
