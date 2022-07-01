package com.mtstream.shelved;

import com.mtstream.shelved.init.BlockInit;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class PlaceableItemsMap {
	public static Object2ObjectArrayMap<Item, Block> map = new Object2ObjectArrayMap<>();
	public static Block getBlock(Item item) {
		map.put(Items.NAUTILUS_SHELL, BlockInit.PLACED_NAUTILUS_SHELL.get());
		map.put(Items.NETHER_STAR, BlockInit.PLACED_NETHER_STAR.get());
		map.put(Items.APPLE, BlockInit.PLACED_APPLE.get());
		map.put(Items.CHORUS_FRUIT, BlockInit.PLACED_CHORUS_FRUIT.get());
		map.put(Items.GOLDEN_APPLE, BlockInit.PLACED_GOLDEN_APPLE.get());
		map.put(Items.ENCHANTED_GOLDEN_APPLE, BlockInit.PLACED_ENCHANTED_GOLDEN_APPLE.get());
		map.put(Items.SNOWBALL, BlockInit.PLACED_SNOWBALL.get());
		map.put(Items.COOKIE, BlockInit.PLACED_COOKIE.get());
		map.put(Items.POTION, BlockInit.PLACED_POTION.get());
		map.put(Items.TOTEM_OF_UNDYING, BlockInit.PLACED_TOTEM_OF_UNDYING.get());
		map.put(Items.COMPASS, BlockInit.PLACED_COMPASS.get());
		map.put(Items.BREAD, BlockInit.PLACED_BREAD.get());
		map.put(Items.PUFFERFISH, BlockInit.PLACED_PUFFERFISH.get());
		map.put(Items.GLASS_BOTTLE, BlockInit.PLACED_GLASS_BOTTLE.get());
		map.put(Items.EXPERIENCE_BOTTLE, BlockInit.PLACED_XP_BOTTLE.get());
		map.put(Items.DRAGON_BREATH, BlockInit.PLACED_DRAGON_BREATH.get());
		return map.get(item);
	}
}
