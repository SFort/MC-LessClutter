package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(value = Screen.class, priority = 4142)
public abstract class Item {

	@Inject(method = "getTooltipFromItem(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
			at = @At("RETURN"), cancellable = true)
	private static void arg(CallbackInfoReturnable<List<Text>> info){
		info.setReturnValue(info.getReturnValue().stream().limit(1).collect(Collectors.toList()));
	}
}

