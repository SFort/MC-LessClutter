package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.ssf.sfort.lessclutter.mixin.access.Scren;
import tf.ssf.sfort.lessclutter.mixin.access.TextButton;

@Mixin(value = InventoryScreen.class, priority = 2022)
public abstract class InvBook extends AbstractInventoryScreen<PlayerScreenHandler> {
	public InvBook(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
		super(screenHandler, playerInventory, text);
	}

	@Shadow @Final private static Identifier RECIPE_BUTTON_TEXTURE;

	@Inject(method = "init()V", at=@At("TAIL"))
	public void init(CallbackInfo ci) {
		if(!this.client.interactionManager.hasCreativeInventory()){
			for (Drawable draw : ((Scren) this).getDrawables())
				if (draw instanceof TexturedButtonWidget && ((TextButton) draw).getTexture().equals(RECIPE_BUTTON_TEXTURE))
					((TexturedButtonWidget) draw).visible = false;
		}
	}
}
