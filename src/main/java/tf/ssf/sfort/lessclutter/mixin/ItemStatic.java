package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = Screen.class, priority = 4641)
public abstract class ItemStatic {

	@Shadow
	protected MinecraftClient client;

	@Inject(method = "renderTooltipFromComponents(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;II)V",
			at = @At(value = "HEAD"), cancellable = true)
	public void renderOrderedTooltip(MatrixStack matrices, List<TooltipComponent> lines, int x, int y, CallbackInfo info) {
		matrices.push();
		y=10;
		Matrix4f matrix4f = matrices.peek().getPositionMatrix();
		VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
		for(x=0; x<lines.size(); x++){
			lines.get(x).drawText(client.textRenderer, 10, y, matrix4f, immediate);
			y += lines.get(x).getWidth(client.textRenderer) == 0 ? 3 : 10;
		}
		immediate.draw();
		matrices.pop();
		info.cancel();
	}
}

