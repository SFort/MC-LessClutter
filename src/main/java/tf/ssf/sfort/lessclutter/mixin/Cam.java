package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class Cam {
    @Shadow
    public float cameraY;
    @Shadow
    private Entity focusedEntity;
    @Inject(method = "updateEyeHeight", at = @At("INVOKE"),cancellable = true)
    public void updateEyeHeight(CallbackInfo info){
        cameraY = focusedEntity.getStandingEyeHeight();
        info.cancel();
    }
}
