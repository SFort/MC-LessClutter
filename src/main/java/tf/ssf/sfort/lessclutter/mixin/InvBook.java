package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.ssf.sfort.lessclutter.mixin.access.TextButton;

@Mixin(value = Screen.class, priority = 2022)
public abstract class InvBook {

	private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");

	@Inject(method = "addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", at=@At("HEAD"))
	private <T extends Element & Drawable & Selectable> void init(T draw, CallbackInfoReturnable<T> cir) {
				if (draw instanceof TexturedButtonWidget && ((TextButton) draw).getTexture().equals(RECIPE_BUTTON_TEXTURE))
					((TexturedButtonWidget) draw).visible = false;
	}
}
