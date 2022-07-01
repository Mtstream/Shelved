package com.mtstream.shelved.init;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.mtstream.shelved.Shelved;
import com.mtstream.shelved.block.PlacedAppleBlock;
import com.mtstream.shelved.block.PlacedBottleBlock;
import com.mtstream.shelved.block.PlacedBreadBlock;
import com.mtstream.shelved.block.PlacedCompassBlock;
import com.mtstream.shelved.block.PlacedCookieBlock;
import com.mtstream.shelved.block.PlacedNautilusShellBlock;
import com.mtstream.shelved.block.PlacedNetherStarBlock;
import com.mtstream.shelved.block.PlacedPotionBlock;
import com.mtstream.shelved.block.PlacedPufferFishBlock;
import com.mtstream.shelved.block.PlacedSnowBallBlock;
import com.mtstream.shelved.block.PlacedTotemOfUndyingBlock;
import com.mtstream.shelved.util.ModSoundTypes;

import it.unimi.dsi.fastutil.objects.Object2BooleanArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Shelved.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;
		
	public static final RegistryObject<Block> PLACED_NAUTILUS_SHELL = register("nautilus_shell_block",
			() -> new PlacedNautilusShellBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).dynamicShape(), Items.NAUTILUS_SHELL), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_APPLE = register("apple_block",
			() -> new PlacedAppleBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).dynamicShape(), Items.APPLE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_CHORUS_FRUIT = register("chorus_fruit_block",
			() -> new PlacedAppleBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).dynamicShape(), Items.CHORUS_FRUIT), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_GOLDEN_APPLE = register("golden_apple_block",
			() -> new PlacedAppleBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).dynamicShape(), Items.GOLDEN_APPLE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_ENCHANTED_GOLDEN_APPLE = register("enchanted_golden_apple_block",
			() -> new PlacedAppleBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).dynamicShape(), Items.ENCHANTED_GOLDEN_APPLE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_SNOWBALL = register("snowball_block",
			() -> new PlacedSnowBallBlock(BlockBehaviour.Properties.copy(Blocks.SNOW).dynamicShape(), Items.SNOWBALL), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_COOKIE = register("cookie_block",
			() -> new PlacedCookieBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).dynamicShape(), Items.COOKIE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_COMPASS = register("compass_block",
			() -> new PlacedCompassBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).dynamicShape(), Items.COMPASS), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_BREAD = register("bread_block",
			() -> new PlacedBreadBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).dynamicShape(), Items.BREAD), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_TOTEM_OF_UNDYING = register("totem_of_undying_block",
			() -> new PlacedTotemOfUndyingBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).dynamicShape(), Items.TOTEM_OF_UNDYING), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_NETHER_STAR = register("nether_star_block",
			() -> new PlacedNetherStarBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).dynamicShape().noCollission().emissiveRendering((s,l,p)->true).lightLevel(s->5), Items.NETHER_STAR), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_POTION = register("potion_block",
			() -> new PlacedPotionBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).instabreak().sound(SoundType.GLASS).dynamicShape(), Items.POTION), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_GLASS_BOTTLE = register("glass_bottle_block",
			() -> new PlacedBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().dynamicShape(), Items.GLASS_BOTTLE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_XP_BOTTLE = register("experience_bottle_block",
			() -> new PlacedBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().dynamicShape(), Items.EXPERIENCE_BOTTLE), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_DRAGON_BREATH = register("dragon_breath_block",
			() -> new PlacedBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().dynamicShape(), Items.DRAGON_BREATH), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	public static final RegistryObject<Block> PLACED_PUFFERFISH = register("pufferfish_block",
			() -> new PlacedPufferFishBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).instabreak().sound(ModSoundTypes.PUFFERFISH).dynamicShape(), Items.PUFFERFISH), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	
	
	public static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> sup){
		return BLOCKS.register(name, sup);
	}
	public static <T extends Block> RegistryObject<T> register(final String name,final Supplier<? extends T> sup,
		Function<RegistryObject<T>, Supplier<? extends Item>> item){
		RegistryObject<T> obj = registerBlock(name, sup);
		ITEMS.register(name, item.apply(obj));
		return obj;
	}
		
}
