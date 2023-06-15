package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = DrawContext.class, priority = 4641)
public abstract class ItemStatic {

	@Shadow @Final private MatrixStack matrices;

	@Inject(method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
			at = @At(value = "HEAD"), cancellable = true)
	public void renderOrderedTooltip(TextRenderer textRenderer, List<TooltipComponent> lines, int x, int y, TooltipPositioner positioner, CallbackInfo info) {
		matrices.push();
		y=10;
		Matrix4f matrix4f = this.matrices.peek().getPositionMatrix();
		VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
		for(x=0; x<lines.size(); x++){
			lines.get(x).drawText(textRenderer, 10, y, matrix4f, immediate);
			y += lines.get(x).getWidth(textRenderer) == 0 ? 3 : 10;
		}
		immediate.draw();
		matrices.pop();
		info.cancel();
	}
}

