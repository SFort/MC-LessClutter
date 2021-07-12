package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class, priority = 3702)
public abstract class HudVignotRed {
	@Shadow public float vignetteDarkness;

	@Inject(method = "updateVignetteDarkness(Lnet/minecraft/entity/Entity;)V", cancellable = true, at = @At("TAIL"))
	private void vignyet(CallbackInfo ci) {
		this.vignetteDarkness = Config.Vignette;
	}
}
