package tesseract.controller;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import tesseract.api.fluid.FluidController;
import tesseract.api.fluid.FluidData;
import tesseract.api.fluid.IFluidNode;

import javax.annotation.Nonnull;

// TODO: Make explosions depend on pressure, capacity, temperature
public class Fluid<T> extends FluidController<T, IFluidNode<T>> {

    /**
     * Creates instance of the tesseract.controller.
     *
     * @param dim The dimension id.
     */
    public Fluid(Object dim) {
        super(dim);
    }

    @Override
    public void onPipeOverPressure(Object dim, long pos, int pressure) {
        Utils.getServerWorld(dim).ifPresent(w -> Utils.createExplosion(w, BlockPos.fromLong(pos), 4.0F, Explosion.Mode.BREAK));
    }

    @Override
    public void onPipeOverCapacity(Object dim, long pos, int capacity) {
        Utils.getServerWorld(dim).ifPresent(w -> Utils.createExplosion(w, BlockPos.fromLong(pos), 1.0F, Explosion.Mode.NONE));
    }

    @Override
    public void onPipeOverTemp(Object dim, long pos, int temperature) {
        Utils.getServerWorld(dim).ifPresent(w -> w.setBlockState(BlockPos.fromLong(pos), temperature >= Fluids.LAVA.getAttributes().getTemperature() ? Blocks.LAVA.getDefaultState() : Blocks.FIRE.getDefaultState()));
    }

    @Override
    public void onPipeGasLeak(Object dim, long pos, @Nonnull FluidData<T> fluid) {
        T resource = fluid.getStack();
       // resource.setAmount((int)(resource.getAmount() * AntimatterConfig.GAMEPLAY.PIPE_LEAK));
    }
}
