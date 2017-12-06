import java.util.NoSuchElementException;

/**
 * Class CompleteGraph encapsulates a complete undirected graph.
 *
 * @author  Alan Kaminsky
 * @version 12-Oct-2016
 */
public class CompleteGraph
	implements GraphSpec
	{

	private int V;       // Number of vertices
	private int v1, v2;  // Vertices joined by current edge

	/**
	 * Construct a new complete graph.
	 *
	 * @param  V  Number of vertices, V &ge; 0.
	 *
	 * @exception  IllegalArgumentException
	 *     (unchecked exception) Thrown if V is out of range.
	 */
	public CompleteGraph
		(int V)
		{
		// Verify preconditions.
		this.V = V;
		if (V < 0)
			throw new IllegalArgumentException (String.format
				("CompleteGraph(): V = %d illegal", V));

		// Initialize fields.
		v1 = 0;
		v2 = 1;
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
		return V*(V - 1)/2;
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
		// Verify preconditions.
		if (v1 >= V - 1)
			throw new NoSuchElementException();

		// Generate the current edge (v1,v2).
		e.v1 = v1;
		e.v2 = v2;

		// Go to the next edge.
		++ v2;
		if (v2 == V)
			{
			++ v1;
			v2 = v1 + 1;
			}
		}

	}
