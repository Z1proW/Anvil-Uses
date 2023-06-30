package ziprow.anviluses;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class AnvilUses implements ModInitializer
{

	public static final String MOD_ID = "anviluses";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("AnvilUses is initializing!");

		ItemTooltipCallback.EVENT.register(this::onItemTooltip);
	}

	private void onItemTooltip(ItemStack item, TooltipContext context, List<Text> tooltip)
	{
		if(!item.hasNbt()) return;

		assert item.getNbt() != null;

		if(item.getNbt().contains("RepairCost"))
		{
			int repairCost = item.getNbt().getInt("RepairCost");

			if(repairCost == 0) return;

			tooltip.add(Text.of("Anvil Uses: " + log2(repairCost + 1)));
		}
	}

	private int log2(int n)
	{
		// if(n == 0) return 0;
		return 31 - Integer.numberOfLeadingZeros(n);
	}

}
