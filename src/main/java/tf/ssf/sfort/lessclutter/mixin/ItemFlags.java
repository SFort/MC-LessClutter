package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, priority = 4142)
public abstract class ItemFlags {

	@Inject(method = "getHideFlags()I",
			at = @At("RETURN"), cancellable = true)
	public void arg(CallbackInfoReturnable<Integer> cir){
		cir.setReturnValue(cir.getReturnValue() | ConfigMixin.hideflags);
	}
}

