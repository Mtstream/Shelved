package com.mtstream.shelved.blockEntity;

import com.mtstream.shelved.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PlacedEnchantedBookBlockEntity extends BlockEntity{
	
	public ListTag enchantments;
	
	public PlacedEnchantedBookBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.PLACED_ENCHANTED_BOOK.get(), pos, state);
	}
	
	@Override
	protected void saveAdditional(CompoundTag cpt) {
		super.saveAdditional(cpt);
		cpt.put(EnchantedBookItem.TAG_STORED_ENCHANTMENTS, enchantments);
	}
	@Override
	public void load(CompoundTag cpt) {
		super.load(cpt);
		enchantments = cpt.getList(EnchantedBookItem.TAG_STORED_ENCHANTMENTS, 10);
	}
	
	@Override
	public void setChanged() {
		requestModelDataUpdate();
		super.setChanged();
		if(this.level == null || this.level.isClientSide) {
			return;
		}
		this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
	}
	
	public void setEnchantment(ItemStack stack) {
		this.enchantments = EnchantedBookItem.getEnchantments(stack);
		this.setChanged();
	}
	
	public ListTag getEnchantment() {
		this.setChanged();
		return this.enchantments;
	}
	
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}
	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getTag());
	}
}
