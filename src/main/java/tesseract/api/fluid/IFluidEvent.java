package tesseract.api.fluid;

/**
 * Interface for handling a fluid events. (Controller will handle them)
 */
public interface IFluidEvent<T> {

    /**
     * Executes when the cable trying to transport higher amount of pressure than can.
     * @param dim The dimension id.
     * @param pos The pipe position.
     * @param pressure The current pressure.
     */
    default void onPipeOverPressure(Object dim, long pos, int pressure) {
        //NOOP
    }

    /**
     * Executes when the cable trying to transport higher amount of liquids than can.
     * @param dim The dimension id.
     * @param pos The pipe position.
     * @param capacity The current capacity.
     */
    default void onPipeOverCapacity(Object dim, long pos, int capacity) {
        //NOOP
    }

    /**
     * Executes when the cable trying to transport higher amount of temperature than can.
     * @param dim The dimension id.
     * @param pos The pipe position.
     * @param temperature The current temperature.
     */
    default void onPipeOverTemp(Object dim, long pos, int temperature) {
        //NOOP
    }

    /**
     * Executes when the pipe trying to transport gas than can.
     * @param dim The dimension id.
     * @param pos The pipe position.
     * @param fluid FluidData holding the Fluid to be queried.
     */
    default void onPipeGasLeak(Object dim, long pos, FluidData<T> fluid) {
        //NOOP
    }
}
