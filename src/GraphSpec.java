/**
 * Interface GraphSpec specifies the interface for an undirected graph.
 *
 * @author  Alan Kaminsky
 * @version 22-Sep-2017
 */
public interface GraphSpec
{

    /**
     * Returns the number of vertices in this graph, V.
     */
    public int V();

    /**
     * Returns the number of edges in this graph, E.
     */
    public int E();

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
    (Edge e);

}