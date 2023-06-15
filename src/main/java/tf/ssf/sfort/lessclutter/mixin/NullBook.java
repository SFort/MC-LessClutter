package tf.ssf.sfort.lessclutter.mixin;

import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeBookWidget.class, priority = 2212)
public class NullBook {

    @Inject(at=@At("HEAD"), method = "initialize", cancellable = true)
    public void initialize(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "reset", cancellable = true)
    public void reset(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "toggleOpen", cancellable = true)
    public void toggleOpen(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "isOpen", cancellable = true)
    public void isOpen(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at=@At("HEAD"), method = "setOpen", cancellable = true)
    protected void setOpen(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "slotClicked", cancellable = true)
    public void slotClicked(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "update", cancellable = true)
    public void update(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "render", cancellable = true)
    public void render(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "getType", cancellable = true)
    public void getType(CallbackInfoReturnable<Selectable.SelectionType> cir) {
        cir.setReturnValue(Selectable.SelectionType.NONE);
    }

    @Inject(at=@At("HEAD"), method = "appendNarrations", cancellable = true)
    public void appendNarrations(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "drawTooltip", cancellable = true)
    public void drawTooltip(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "drawGhostSlots", cancellable = true)
    public void drawGhostSlots(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "mouseClicked", cancellable = true)
    public void mouseClicked(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at=@At("HEAD"), method = "isClickOutsideBounds", cancellable = true)
    public void isClickOutsideBounds(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(at=@At("HEAD"), method = "keyPressed", cancellable = true)
    public void keyPressed(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at=@At("HEAD"), method = "keyReleased", cancellable = true)
    public void keyReleased(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at=@At("HEAD"), method = "charTyped", cancellable = true)
    public void charTyped(char chr, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at=@At("HEAD"), method = "refresh", cancellable = true)
    public void refresh(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "onRecipesDisplayed", cancellable = true)
    public void onRecipesDisplayed(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "showGhostRecipe", cancellable = true)
    public void showGhostRecipe(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "acceptAlignedInput", cancellable = true)
    public void acceptAlignedInput(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at=@At("HEAD"), method = "sendBookDataPacket", cancellable = true)
    protected void sendBookDataPacket(CallbackInfo ci) {
        ci.cancel();
    }
}
