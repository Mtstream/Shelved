package com.mtstream.shelved.init;

import com.mtstream.shelved.Shelved;
import com.mtstream.shelved.blockEntity.PlacedPotionBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Shelved.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<PlacedPotionBlockEntity>> PLACED_POTION = BLOCK_ENTITY.register("placed_potion", 
			()->BlockEntityType.Builder.of(PlacedPotionBlockEntity::new, BlockInit.PLACED_POTION.get()).build(null));
}
