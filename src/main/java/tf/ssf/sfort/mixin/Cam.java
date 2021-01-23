package tf.ssf.sfort.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.ssf.sfort.Config;

@Mixin(Camera.class)
public class Cam {
    @Shadow
    public float cameraY;
    @Shadow
    private Entity focusedEntity;
    @Inject(method = "updateEyeHeight", at = @At("INVOKE"),cancellable = true)
    public void updateEyeHeight(CallbackInfo info){
        //Still no clue how todo conditional mixins...
        //and at this point ive spent too much time trying to figure it out
        if(Config.instantCrouch){
            cameraY = focusedEntity.getStandingEyeHeight();
            info.cancel();
        }
    }
}
