package com.mdj20.quickgraph.quickgraph.main;
import java.util.List;

/** A pair of type V vertex used in the Graph hierarchy. Denotes a connection between to vertices. 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Type of vertex that the edge connects.
 */

public interface Edge<V> {
	
	/**
	 * Returns a unmodifiable list containing 2 vertices.
	 *
	 *  
	 * @return List<V> of vertices 
	 */
	
	public List<V> getVertices();
	/** Returns vertex specified by index.
	 * 
	 * @param index must be either 0 or 1 
	 * @return V one of 2 vertices, or null if caller passes int !(1 || 0)
	 */
	
	public V getVertex(int index);
	
	/**
	 * Returns the vertex that opposes the vertex being passed. Returns null if edge doesn't contain vertex being passed.
	 * 
	 * 
	 * 
	 * @param vertex 
	 * @return V opposite vertex
	 */
	public V getOpposingVertex(V vertex);
	

	
	/**
	 * Returns index of vertex, returns -1 if vertex is not connected by this edge.
	 * 
	 * @param vertex vertex to search for.
	 * @return index of vertex or -1 if vertex isn't found.
	 */
	
	public int indexOf(V vertex);
	
	
	/** 
	 * Convenience method determines if the passed edge is the reciprocal of this edge.
	 * <p>
	 * Used to determine edge equivalence in an undirected graph, 
	 * i.e. edge0 connects A->B, if edge1 B->A is passed to isReciprical method it will return true, 
	 * this holds true even if the edges are directed. 
	 * @param edge reference to edge whose reciprocity is to be determined.
	 * @return true if passed edge is reciprocal of object edge.
	 */
	
	public boolean isReciprocal(Edge<V> edge);
	
	/** 
	 * Convenience method determines if the passed edge is parallel to this edge.
	 * 
	 * 
	 * @param edge reference to edge that is to be determined parallel.
	 * @return true if passed edge is parallel to this edge.
	 */
	
	public boolean isParallel(Edge<V> edge);
	
}
