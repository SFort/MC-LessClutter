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
    private float cameraY;
    @Shadow
    private Entity focusedEntity;
    @Inject(method = "updateEyeHeight", at = @At("TAIL"),cancellable = true)
    public void updateEyeHeight(CallbackInfo info){
        if(focusedEntity != null) {
            cameraY = focusedEntity.getStandingEyeHeight();
            info.cancel();
        }
    }
}
