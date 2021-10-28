package tf.ssf.sfort.lessclutter;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import tf.ssf.sfort.script.ScriptingScreen;
import tf.ssf.sfort.script.instance.PlayerEntityScript;

public class ConfigScreen extends Screen {
    final Screen screen;
    ConfigScreen(Screen parent) {
        super(new LiteralText("Less Clutter"));
        this.screen = parent;
    }
    @Override
    public void init(){
        addDrawableChild(new ButtonWidget(width/2-75, height/2-30, 150, 20, new LiteralText("Dynamic Crosshair Script"),
                p ->{
                    client.setScreen(new ScriptingScreen(
                            new LiteralText("Less Clutter Script"),
                            this,
                            new ScriptingScreen.Script(
                                    "§bLess Clutter - Crosshair",
                                    new PlayerEntityScript<ClientPlayerEntity>(),
                                    Config::writeCross,
                                    null,
                                    Config::readCross,
                                    ScriptingScreen.getDefaultEmbed()
                            )
                    ));
                }));
    }
    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        int x = width/2-130;
        int y = height/2-80;
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, delta);
        int i = textRenderer.getWidth("\".minecraft/config/LessClutter.conf\"");
        textRenderer.drawWithShadow(matrix,"UI doesn't allow editing non-scripting options yet", width/2-123, height/2-70, -1);
        textRenderer.drawWithShadow(matrix,".minecraft/config/LessClutter.conf", width/2-82, height/2-50, -1);
        fill(matrix, x, y, x+260, y+1, -1);
        fill(matrix, x, y, x+1, y+130, -1);
        fill(matrix, x, y+130-1, x+260, y+130, -1);
        fill(matrix, x+260-1, y, x+260, y+130, -1);
    }
    @Override
    public void onClose(){
        client.setScreen(screen);
    }
}