package zabi.minecraft.extraalchemy.client.screen;

import java.lang.reflect.Field;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import zabi.minecraft.extraalchemy.config.ConfigInstance;
import zabi.minecraft.extraalchemy.config.ModConfig;

public class ConfigScreenProvider implements ModMenuApi {
	
	private static final Text MUST_SYNC = new TranslatableText("extraalchemy.config.must_sync").setStyle(Style.EMPTY.withColor(Formatting.RED).withBold(true));
	private static final Text CLIENT_SIDE = new TranslatableText("extraalchemy.config.clientside").setStyle(Style.EMPTY.withColor(Formatting.AQUA).withBold(true));
	private static final Text SERVER_SIDE = new TranslatableText("extraalchemy.config.serveronly").setStyle(Style.EMPTY.withColor(Formatting.GOLD).withBold(true));
	
	public static ConfigBuilder builder() {
		
		ConfigBuilder configBuilder = ConfigBuilder.create()
				.setTitle(new TranslatableText("extraalchemy.mod_name"))
				.setEditable(true)
				.setSavingRunnable(() -> ModConfig.writeJson());
		
		ConfigCategory general = configBuilder.getOrCreateCategory(new TranslatableText("extraalchemy.config.general"));
		ConfigCategory potions = configBuilder.getOrCreateCategory(new TranslatableText("extraalchemy.config.potions"));
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.enable_learning_boost") , ModConfig.INSTANCE.learningIncreasesExpOrbValue)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.enable_learning_boost.tooltip1"),
							new TranslatableText("extraalchemy.config.general.enable_learning_boost.tooltip2"), 
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.learningIncreasesExpOrbValue = val;})
					.build()
		);
		
//		general.addEntry(configBuilder.entryBuilder()
//				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.disable_inventory_shift") , ModConfig.INSTANCE.removeInventoryPotionShift)
//					.setDefaultValue(true)
//					.setTooltip(
//							new TranslatableText("extraalchemy.config.general.disable_inventory_shift.tooltip1"), 
//							new TranslatableText("extraalchemy.config.general.disable_inventory_shift.tooltip2"),
//							CLIENT_SIDE
//					)
//					.setSaveConsumer(val -> {ModConfig.INSTANCE.removeInventoryPotionShift = val;})
//					.build()
//		);
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.show_icons_in_tooltips") , ModConfig.INSTANCE.showIconsInTooltips)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.show_icons_in_tooltips.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.show_icons_in_tooltips.tooltip2"),
							CLIENT_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.showIconsInTooltips = val;})
					.build()
		);
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.enable_vials") , ModConfig.INSTANCE.enableVials)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.enable_vials.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.enable_vials.tooltip2"),
							MUST_SYNC
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.enableVials = val;})
					.build()
		);
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.enable_rings") , ModConfig.INSTANCE.enableRings)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.enable_rings.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.enable_rings.tooltip2"),
							MUST_SYNC
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.enableRings = val;})
					.build()
		);
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.ignore_ring_mods") , ModConfig.INSTANCE.allowRingsInInventoryWithThirdPartyMods)
					.setDefaultValue(false)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.ignore_ring_mods.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.ignore_ring_mods.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.allowRingsInInventoryWithThirdPartyMods = val;})
					.build()
		);
		
		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.enable_brewing_stand_fire") , ModConfig.INSTANCE.enableBrewingStandFire)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.enable_brewing_stand_fire.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.enable_brewing_stand_fire.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.enableBrewingStandFire = val;})
					.build()
		);

		general.addEntry(configBuilder.entryBuilder()
				.startIntField(new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay") , ModConfig.INSTANCE.brewingStandHeatIncrementDelay)
					.setDefaultValue(2)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.brewingStandHeatIncrementDelay = val;})
					.build()
		);


		general.addEntry(configBuilder.entryBuilder()
				.startIntField(new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity") , ModConfig.INSTANCE.brewingStandFireMaxCapacity)
					.setDefaultValue(20)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.brewingStandFireMaxCapacity = val;})
					.build()
		);

		general.addEntry(configBuilder.entryBuilder()
				.startIntField(new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay") , ModConfig.INSTANCE.brewingStandHeatIncrementDelay)
					.setDefaultValue(2)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.brewing_stand_heat_increment_delay.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.brewingStandHeatIncrementDelay = val;})
					.build()
		);


		general.addEntry(configBuilder.entryBuilder()
				.startIntField(new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity") , ModConfig.INSTANCE.brewingStandFireMaxCapacity)
					.setDefaultValue(20)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.brewing_stand_fire_max_capacity.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.brewingStandFireMaxCapacity = val;})
					.build()
		);

		general.addEntry(configBuilder.entryBuilder()
				.startBooleanToggle(new TranslatableText("extraalchemy.config.general.anchor_depletes") , ModConfig.INSTANCE.useAnchorChargesWithReturnPotion)
					.setDefaultValue(true)
					.setTooltip(
							new TranslatableText("extraalchemy.config.general.anchor_depletes.tooltip1"), 
							new TranslatableText("extraalchemy.config.general.anchor_depletes.tooltip2"),
							SERVER_SIDE
					)
					.setSaveConsumer(val -> {ModConfig.INSTANCE.useAnchorChargesWithReturnPotion = val;})
					.build()
		);
		
		try {
			for (Field f:ConfigInstance.Potions.class.getDeclaredFields()) {
				String name = f.getName();
				if (f.getType().isAssignableFrom(boolean.class)) {
					potions.addEntry(configBuilder.entryBuilder()
						.startBooleanToggle(new TranslatableText("extraalchemy.config.potion", new TranslatableText("item.minecraft.potion.effect."+name)) , f.getBoolean(ModConfig.INSTANCE.potions))
							.setDefaultValue(true)
							.requireRestart()
							.setTooltip(
									new TranslatableText("extraalchemy.config.potion.tooltip1", new TranslatableText("item.minecraft.potion.effect."+name)), 
									new TranslatableText("extraalchemy.config.potion.tooltip2", new TranslatableText("item.minecraft.potion.effect."+name)),
									MUST_SYNC
							)
							.setSaveConsumer(val -> {try {
								f.setBoolean(ModConfig.INSTANCE.potions, val);
							} catch (Exception e) {
								e.printStackTrace();
							}})
							.build()
							
					);
				}
			}
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}
		return configBuilder;
	}
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			return builder().setParentScreen(parent).build();
		};
	}

}
