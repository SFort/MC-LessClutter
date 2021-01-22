package tf.ssf.sfort.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.ssf.sfort.Config;

@Mixin(value = InGameHud.class, priority = 2023)
public abstract class Hud {

	@Shadow
	private ItemStack currentStack;
	@Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
	private void renderCrosshair(MatrixStack matrices, CallbackInfo info) {
		if (!(currentStack.getItem() instanceof BlockItem) && Config.dynamicCrosshair)
			info.cancel();
	}
	@Shadow
	private int heldItemTooltipFade;

	@Inject(at = @At("HEAD"), method = "renderHeldItemTooltip")
	public void renderHeldItemTooltip(MatrixStack matrixStack, CallbackInfo info){
		//I can't figure out how todo conditional injections
		//so here i go writing this shit
		if (Config.removedHudName)
			heldItemTooltipFade = 0;
	}
}
