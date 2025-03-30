package ziprow.anviluses;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AnvilUses implements ModInitializer
{

	public static final String MOD_ID = "anviluses";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("AnvilUses is initializing!");

		ItemTooltipCallback.EVENT.register((item, context, type, tooltip) -> onItemTooltip(item, tooltip));
	}

	private void onItemTooltip(ItemStack item, List<Text> tooltip)
	{
		if(!item.getComponents().contains(DataComponentTypes.REPAIR_COST)) return;

		int repairCost = item.getOrDefault(DataComponentTypes.REPAIR_COST, 0);

		if(repairCost == 0) return;

		tooltip.add(Text.of("Anvil Uses: " + log2(repairCost + 1)));
	}

	private int log2(int n)
	{
		// if(n == 0) return 0;
		return 31 - Integer.numberOfLeadingZeros(n);
	}

}
