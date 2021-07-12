package tf.ssf.sfort.lessclutter;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class NullWidget extends RecipeBookWidget {
    public NullWidget() {
        super();
    }

    @Override
    public void initialize(int parentWidth, int parentHeight, MinecraftClient client, boolean narrow, AbstractRecipeScreenHandler<?> craftingScreenHandler) {
    }

    @Override
    public void close() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void toggleOpen() {
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    protected void setOpen(boolean opened) {
    }

    @Override
    public void slotClicked(@Nullable Slot slot) {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    }

    @Override
    public void drawTooltip(MatrixStack matrices, int i, int j, int k, int l) {
    }

    @Override
    public void drawGhostSlots(MatrixStack matrices, int i, int j, boolean bl, float f) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public boolean isClickOutsideBounds(double d, double e, int i, int j, int k, int l, int m) {
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return false;
    }

    @Override
    public void refresh() {
    }

    @Override
    public void onRecipesDisplayed(List<Recipe<?>> recipes) {
    }

    @Override
    public void showGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
    }

    @Override
    public void acceptAlignedInput(Iterator<Ingredient> inputs, int slot, int amount, int gridX, int gridY) {
    }

    @Override
    protected void sendBookDataPacket() {
    }
}
