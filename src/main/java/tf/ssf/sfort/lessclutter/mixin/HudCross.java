package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.ssf.sfort.lessclutter.Config;

@Mixin(value = InGameHud.class, priority = 2023)
public abstract class HudCross {

	@Shadow @Final
	private MinecraftClient client;

	@Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
	private void renderCrosshair(MatrixStack matrices, CallbackInfo info) {
		if (Config.dynamicCrosshair.test(client.player))
			info.cancel();
	}
}
