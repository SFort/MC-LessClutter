package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
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
	public void renderHeldItemTooltip(MatrixStack matrixStack, CallbackInfo info){
		heldItemTooltipFade = 0;
	}
}
