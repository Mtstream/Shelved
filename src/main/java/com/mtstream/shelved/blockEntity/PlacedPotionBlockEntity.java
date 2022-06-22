package com.mtstream.shelved.blockEntity;

import java.util.List;

import com.mtstream.shelved.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PlacedPotionBlockEntity extends BlockEntity{
	public Potion potion = Potions.EMPTY;
	
	public boolean hasEffect;

	public PlacedPotionBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.PLACED_POTION.get(), pos, state);
	}
	
	@Override
	protected void saveAdditional(CompoundTag cpt) {
		super.saveAdditional(cpt);
		ResourceLocation loc = Registry.POTION.getKey(potion);
		cpt.putString("potion", loc.toString());
		cpt.putBoolean("has_effect", potion.hasInstantEffects());
	}
	@Override
	public void load(CompoundTag cpt) {
		super.load(cpt);
		potion = PotionUtils.getPotion(cpt);
	}
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}
}
