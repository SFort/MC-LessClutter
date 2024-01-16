package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value=RecipeBookWidget.class, priority = 2022)
public abstract class InvBook {

	@Inject(method="keyPressed(III)Z", at=@At("HEAD"))
	private void key(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(method="mouseClicked(DDI)Z", at=@At("HEAD"))
	private void mouse(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(method="render(Lnet/minecraft/client/gui/DrawContext;IIF)V", at=@At("HEAD"))
	private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		ci.cancel();
	}
}
