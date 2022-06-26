package com.mtstream.shelved.blockEntity;

import java.util.List;

import com.mtstream.shelved.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlacedPotionBlockEntity extends BlockEntity{
	public Potion potion = Potions.EMPTY;
	
	public PlacedPotionBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.PLACED_POTION.get(), pos, state);
	}
	public void setPotion(ItemStack stack,BlockPos pos) {
		this.potion = PotionUtils.getPotion(stack);
		this.update();
	}
	public Potion getPotion() {
		return this.potion;
	}
	public void showPotion(String str, Player pla) {
		CompoundTag cpt = new CompoundTag();
		ResourceLocation loc = Registry.POTION.getKey(this.potion);
		cpt.putString("Potion", loc.toString());
		pla.displayClientMessage(new TextComponent(PotionUtils.getPotion(cpt).getName(str)), true);
	}
	public boolean hasEffect() {
		return this.potion.hasInstantEffects();
	}
	public void update() {
		requestModelDataUpdate();
		setChanged(level, worldPosition, getBlockState());
		saveAdditional(getUpdateTag());
		if(this.level != null) {
			this.level.setBlockAndUpdate(worldPosition, getBlockState());
		}
	}
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}
	@Override
	protected void saveAdditional(CompoundTag cpt) {
		super.saveAdditional(cpt);
		ResourceLocation loc = Registry.POTION.getKey(this.potion);
		cpt.putString(PotionUtils.TAG_POTION, loc.toString());
	}
	@Override
	public void load(CompoundTag cpt) {
		super.load(cpt);
		this.potion = PotionUtils.getPotion(cpt);
	}
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
	      return ClientboundBlockEntityDataPacket.create(this);
	}
	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		load(tag);
	}
	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		load(pkt.getTag());
	}
}
