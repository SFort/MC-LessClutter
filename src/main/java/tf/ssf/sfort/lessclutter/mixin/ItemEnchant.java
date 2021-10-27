package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

import static net.minecraft.item.ItemStack.appendEnchantments;

@Mixin(value = Screen.class, priority = 4142)
public abstract class ItemEnchant {

	@Inject(method = "getTooltipFromItem(Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
			at = @At("RETURN"), cancellable = true)
	public void arg(ItemStack stack, CallbackInfoReturnable<List<Text>> info){
		if (stack.getItem().equals(Items.ENCHANTED_BOOK)) return;
		List<Text> txt = info.getReturnValue().stream().limit(1).collect(Collectors.toList());
		if (stack.hasNbt()) {
			appendEnchantments(txt, stack.getEnchantments());
		}
		info.setReturnValue(txt);
	}
}

