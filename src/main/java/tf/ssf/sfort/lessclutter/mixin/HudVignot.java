package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class, priority = 3702)
public abstract class HudVignot {
	@Inject(method = "updateVignetteDarkness(Lnet/minecraft/entity/Entity;)V", cancellable = true, at = @At("HEAD"))
	private void vignyet(CallbackInfo ci) {
		ci.cancel();
	}
	@Inject(method = "renderVignetteOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/Entity;)V", cancellable = true, at = @At("HEAD"))
	private void vignot(CallbackInfo ci) {
		ci.cancel();
	}
}
