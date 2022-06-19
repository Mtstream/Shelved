package com.mtstream.shelved.init;

import com.google.common.base.Supplier;
import com.mtstream.shelved.Shelved;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Shelved.MOD_ID);
	
	public static <T extends Item> RegistryObject<T> register(final String name,final Supplier<T> sup){
		return ITEMS.register(name, sup);
	}
	
}
