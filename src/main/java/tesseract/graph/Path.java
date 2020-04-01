package tesseract.graph;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import tesseract.api.IConnectable;
import tesseract.util.Node;
import tesseract.util.Pos;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * The Path is a class that should work with paths for grids.
 */
public class Path<C extends IConnectable> {

    private Pos origin;
    private Pos target;
    private Long2ObjectMap<C> full;
    private Long2ObjectMap<C> cross;

    /**
     * Creates a path instance.
     *
     * @param connectors The connectors array.
     * @param path The path queue.
     */
    protected Path(Long2ObjectMap<Connectivity.Cache<C>> connectors, ArrayDeque<Node> path) {
        origin = path.pollLast();
        target = path.pollFirst();

        full = new Long2ObjectLinkedOpenHashMap<>();
        cross = new Long2ObjectLinkedOpenHashMap<>();

        Iterator<Node> iterator = path.descendingIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            long pos = node.get();
            C cable = connectors.get(pos).value();
            full.put(pos, cable);
            if (node.isCrossroad()) {
                cross.put(pos, cable);
            }
        }
    }

    /**
     * @return Gets the origin position.
     */
    public Pos origin() {
        return origin;
    }

    /**
     * @return Gets the target position.
     */
    public Pos target() {
        return target;
    }

    /**
     * @return Gets the full connectors path.
     */
    public Long2ObjectMap<C> getFull() {
        return full;
    }

    /**
     * @return Gets the crossroad connectors path.
     */
    public Long2ObjectMap<C> getCross() {
        return cross;
    }

    /**
     * @return Checks that the path is empty.
     */
    public boolean isEmpty() {
        return origin == null || target == null;
    }
}