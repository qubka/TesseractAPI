package tesseract.api.flux;

import tesseract.api.Consumer;
import tesseract.graph.Path;

/**
 * A class that acts as a container for a item consumer.
 */
public class FluxConsumer extends Consumer<IFluxCable, IFluxNode> {

    private long minCapacity = Long.MAX_VALUE;

    /**
     * Creates instance of the consumer.
     *
     * @param consumer The consumer node.
     * @param path The path information.
     */
    protected FluxConsumer(IFluxNode consumer, Path<IFluxCable> path) {
        super(consumer, path);
    }

    /**
     * Adds energy to the node. Returns quantity of energy that was accepted.
     *
     * @param maxReceive Amount of energy to be inserted.
     * @param simulate If true, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    public long insert(long maxReceive, boolean simulate) {
        return node.insert(maxReceive, simulate);
    }

    /**
     * @return Checks that the min capacity on the path.
     */
    public long getMinCapacity() {
        return minCapacity;
    }

    @Override
    protected void onConnectorCatch(IFluxCable cable) {
        minCapacity = Math.min(minCapacity, cable.getCapacity());
    }
}