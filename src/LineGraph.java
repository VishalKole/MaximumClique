import java.util.NoSuchElementException;

/**
 * Class LineGraph encapsulates an undirected graph consisting of a line of
 * vertices.
 *
 * @author  Alan Kaminsky
 * @version 27-Sep-2017
 */
public class LineGraph
        implements GraphSpec
{

    private int V;          // Number of vertices
    private int E;          // Number of edges
    private int toGenerate; // Next edge to generate

    /**
     * Construct a new line graph.
     *
     * @param  V  Number of vertices, V &ge; 2.
     *
     * @exception  IllegalArgumentException
     *     (unchecked exception) Thrown if V is out of range.
     */
    public LineGraph
    (int V)
    {
        // Verify preconditions.
        this.V = V;
        if (V < 2)
            throw new IllegalArgumentException (String.format
                    ("LineGraph(): V = %d illegal", V));

        // Initialize fields.
        this.E = V - 1;
        toGenerate = 0;
    }

    /**
     * Returns the number of vertices in this graph, V.
     */
    public int V()
    {
        return V;
    }

    /**
     * Returns the number of edges in this graph, E.
     */
    public int E()
    {
        return E;
    }

    /**
     * Obtain the next edge in this graph. This method must be called
     * repeatedly, E times, to obtain all the edges. Each time this method is
     * called, it stores, in the v1 and v2 fields of object e, the vertices
     * connected by the next edge. Each vertex is in the range 0 .. V-1.
     *
     * @param  edge  Edge object in which to store the vertices.
     *
     * @exception  NoSuchElementException
     *     (unchecked exception) Thrown if this method is called more than E
     *     times.
     */
    public void nextEdge
    (Edge e)
    {
        if (toGenerate == V - 1)
            throw new NoSuchElementException();
        e.v1 = toGenerate;
        e.v2 = toGenerate + 1;
        ++ toGenerate;
    }

}
