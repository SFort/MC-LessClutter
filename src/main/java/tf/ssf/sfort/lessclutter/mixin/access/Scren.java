package tf.ssf.sfort.lessclutter.mixin.access;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Selectable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(net.minecraft.client.gui.screen.Screen.class)
public interface Scren {
    @Accessor("drawables")
    List<Drawable> getDrawables();
    @Accessor("selectables")
    List<Selectable> getSelectables();
}
