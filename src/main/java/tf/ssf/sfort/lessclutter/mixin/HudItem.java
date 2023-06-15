package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class, priority = 2022)
public abstract class HudItem {
	@Shadow
	private int heldItemTooltipFade;

	@Inject(at = @At("HEAD"), method = "renderHeldItemTooltip")
	public void renderHeldItemTooltip(DrawContext matrixStack, CallbackInfo info){
		heldItemTooltipFade = 0;
	}
}
