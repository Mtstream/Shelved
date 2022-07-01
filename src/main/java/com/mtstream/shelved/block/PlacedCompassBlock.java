package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedCompassBlock extends WaterPlacedItemBlock{
	
	public static final IntegerProperty DIRECTION = IntegerProperty.create("direction", 1, 8);

	public PlacedCompassBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTION, 1).setValue(WATERLOGGED, false));
	}
	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_,
			CollisionContext p_60558_) {
		return Shapes.box(0.21875, 0, 0.21875, 0.78125, 0.125, 0.78125);
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, DIRECTION);
	}
	public String getDirString(int i) {
		switch(i) {
		case 1:
			return "S";
		case 2:
			return "SW";
		case 3:
			return "W";
		case 4:
			return "NW";
		case 5:
			return "N";
		case 6:
			return "NE";
		case 7:
			return "E";
		case 8:
			return "SE";
			default:
				return "S";
		}
	}
	public void showDirection(int i, Player pla, Level lev) {
		if(!lev.isClientSide) {
			pla.displayClientMessage(new TextComponent(this.getDirString(i)), true);
		}
	}
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult res) {
		float yrot = pla.getYRot();
		int sector = Math.round(yrot/45) >= 0 ? Math.round(yrot/45) + 1 : Math.round(yrot/45) + 9;
		if(!lev.isClientSide) {
			this.showDirection(sector, pla, lev);
			lev.setBlock(pos, state.setValue(DIRECTION, sector), 3);
		}
		return InteractionResult.sidedSuccess(lev.isClientSide);
	}
}
