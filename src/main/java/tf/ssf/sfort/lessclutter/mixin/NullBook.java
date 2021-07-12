package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.ssf.sfort.lessclutter.NullWidget;

@Mixin(value = InventoryScreen.class, priority = 2022)
public abstract class NullBook {
	@Mutable @Final @Shadow
	private RecipeBookWidget recipeBook;
	@Inject(method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;)V", at=@At("TAIL"))
	public void init(CallbackInfo ci){
		recipeBook = new NullWidget();
	}
}
