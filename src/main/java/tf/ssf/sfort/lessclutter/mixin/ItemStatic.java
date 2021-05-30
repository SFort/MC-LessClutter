package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Mixin(value = Screen.class, priority = 4642)
public abstract class ItemStatic {

	@Shadow
	protected MinecraftClient client;

	@Inject(method = "renderOrderedTooltip(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;II)V",
			at = @At(value = "HEAD"), cancellable = true)
	public void renderOrderedTooltip(MatrixStack matrices, List<? extends OrderedText> lines, int x, int y, CallbackInfo info) {
		y=10;
		for(x=0; x<lines.size(); x++){
			client.textRenderer.drawWithShadow(matrices, lines.get(x), 10, y, -1);
			y += client.textRenderer.getWidth(lines.get(x)) == 0 ? 3 : 10;
		}
		info.cancel();
	}
}

