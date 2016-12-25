package com.cout970.magneticraft.block.heat

import com.cout970.magneticraft.block.PROPERTY_DIRECTION
import com.cout970.magneticraft.tileentity.heat.TileHeatSink
import com.cout970.magneticraft.util.get
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Created by cout970 on 04/07/2016.
 */
object BlockHeatSink : BlockHeatMultistate(Material.ROCK, "heat_sink"), ITileEntityProvider {

    override fun createNewTileEntity(worldIn: World?, meta: Int): TileEntity = TileHeatSink()

    override fun onBlockPlacedBy(worldIn: World?, pos: BlockPos, state: IBlockState?, placer: EntityLivingBase, stack: ItemStack?) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
        worldIn?.setBlockState(pos, defaultState.withProperty(PROPERTY_DIRECTION, placer.horizontalFacing.opposite))
    }

    override fun getMetaFromState(state: IBlockState): Int = PROPERTY_DIRECTION[state].ordinal

    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(PROPERTY_DIRECTION, EnumFacing.getHorizontal(meta))

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, PROPERTY_DIRECTION)
}