package tf.ssf.sfort.lessclutter;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import tf.ssf.sfort.script.Default;
import tf.ssf.sfort.script.ScriptingScreen;

public class ConfigScreen extends Screen {
    final Screen screen;
    ConfigScreen(Screen parent) {
        super(Text.of("Less Clutter"));
        this.screen = parent;
    }
    @Override
    public void init(){
        addDrawableChild(ButtonWidget.builder(Text.of("Dynamic Crosshair Script"),
                p ->{
                    client.setScreen(new ScriptingScreen(
                            Text.of("Less Clutter Script"),
                            this,
                            new ScriptingScreen.Script(
                                    "§bLess Clutter - Crosshair",
                                    Default.PLAYER_ENTITY,
                                    Config::writeCross,
                                    null,
                                    Config::readCross,
                                    ScriptingScreen.getDefaultEmbed()
                            )
                    ));
                }).size(150, 20).position(width/2-75, height/2-30).build());
    }
    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        int x = width/2-130;
        int y = height/2-80;
        this.renderBackground(drawContext);
        super.render(drawContext, mouseX, mouseY, delta);
        int i = textRenderer.getWidth("\".minecraft/config/LessClutter.conf\"");
        drawContext.drawTextWithShadow(textRenderer,"UI doesn't allow editing non-scripting options yet", width/2-123, height/2-70, -1);
        drawContext.drawTextWithShadow(textRenderer,".minecraft/config/LessClutter.conf", width/2-82, height/2-50, -1);
        drawContext.fill(x, y, x+260, y+1, -1);
        drawContext.fill(x, y, x+1, y+130, -1);
        drawContext.fill(x, y+130-1, x+260, y+130, -1);
        drawContext.fill(x+260-1, y, x+260, y+130, -1);
    }
    @Override
    public void close(){
        client.setScreen(screen);
    }
}