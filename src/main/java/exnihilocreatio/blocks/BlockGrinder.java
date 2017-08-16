package exnihilocreatio.blocks;

import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.tiles.TileAutoSifter;
import exnihilocreatio.tiles.TileGrinder;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockGrinder extends BlockBase implements ITileEntityProvider {
    public static final PropertyEnum<EnumGrinderParts> PART_TYPE = PropertyEnum.create("part_type", EnumGrinderParts.class);

    public BlockGrinder() {
        super(Material.GLASS, "block_grinder");
        setDefaultState(this.blockState.getBaseState().withProperty(PART_TYPE, EnumGrinderParts.EMPTY));

        setHardness(2);
        setHarvestLevel("axe", 0);

        setCreativeTab(ExNihiloCreatio.tabExNihilo);
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileGrinder();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PART_TYPE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    //region >>>> RENDERING OPTIONS
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    //endregion

    private TileGrinder getTe(World world, BlockPos pos) {
        return (TileGrinder) world.getTileEntity(pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileGrinder te = getTe(worldIn, pos);
        te.facing = placer.getHorizontalFacing();
    }
}
