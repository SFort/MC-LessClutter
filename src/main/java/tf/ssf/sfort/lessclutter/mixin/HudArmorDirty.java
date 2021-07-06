package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 3702)
public abstract class HudArmorDirty {
	@Inject(method = "getArmor()I", at = @At("HEAD"), cancellable = true)
	private void armor(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(0);
	}
}
