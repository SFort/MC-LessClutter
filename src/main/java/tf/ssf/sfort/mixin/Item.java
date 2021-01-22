package tf.ssf.sfort.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.ssf.sfort.Config;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class Item {
	/*
	@Shadow
	private CompoundTag tag;
	@Shadow
	private boolean empty;
	*/
	@Shadow
	public abstract boolean hasCustomName();
	public boolean id$hasCustomName(){return this.hasCustomName();}
	@Shadow
	public abstract Text getName();
	public Text id$getName(){return this.getName();}
	@Shadow
	public abstract Rarity getRarity();
	public Rarity id$getRarity(){return this.getRarity();}
	
	@Inject(at = @At("HEAD"), method = "getTooltip", cancellable = true)
	public void getTooltip(PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> info){
		if (Config.lessTooltips) {
			List<Text> list = Lists.newArrayList();
			MutableText mutableText = (new LiteralText("")).append(id$getName()).formatted(id$getRarity().formatting);
			if (id$hasCustomName()) {
				mutableText.formatted(Formatting.ITALIC);
			}
			list.add(mutableText);
			/*
			if (!this.empty && this.tag != null && !this.tag.isEmpty() && context.isAdvanced()) {
				ListTag enchantments = tag.getList("Enchantments", 10);
				for (int i = 0; i < enchantments.size(); ++i) {
					CompoundTag compoundTag = enchantments.getCompound(i);
					Registry.ENCHANTMENT.getOrEmpty(Identifier.tryParse(compoundTag.getString("id"))).ifPresent((e) -> {
						list.add(e.getName(compoundTag.getInt("lvl")));
					});
				}
			}*/
			info.setReturnValue(list);
		}
	}
}

