package tesseract.api.fluid;


import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.Set;

/**
 * A class that acts as holder of the fluid that has passed thought pipes.
 */
public class FluidHolder<T> {

    private int pressure;
    private final int maxPressure, maxCapacity;
    private final Set<T> fluids = new ObjectOpenHashSet<>();

    /**
     * Creates instance of the holder.
     *
     * @param pipe The pipe connector.
     */
    protected FluidHolder(IFluidPipe pipe) {
        this.maxCapacity = pipe.getCapacity();
        this.maxPressure = pipe.getPressure();
    }

    /**
     * Adds a new liquid.
     *
     * @param pressure The added pressure.
     * @param fluid The fluid type.
     */
    public void add(int pressure, T fluid) {
        this.pressure += pressure;
        fluids.add(fluid);
    }

    /**
     * @return Gets a current pressure.
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * @return Gets a current capacity.
     */
    public int getCapacity() {
        return fluids.size();
    }

    /**
     * @return Checks that the holder is not able to handle pressure.
     */
    public boolean isOverPressure() {
        return maxPressure < pressure;
    }

    /**
     * @return Checks that the holder is not able to handle pressure.
     */
    public boolean isOverCapacity() {
        return maxCapacity < fluids.size();
    }
}
