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

@Mixin(value = InGameHud.class, priority = 2023)
public abstract class HudCross {
	@Shadow
	private ItemStack currentStack;
	@Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
	private void renderCrosshair(MatrixStack matrices, CallbackInfo info) {
		if (Config.dynamicCrosshair && !(currentStack.getItem() instanceof BlockItem))
			info.cancel();
	}
}
