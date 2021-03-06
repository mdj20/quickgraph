package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;

/**
 *  @author Matthew D. Jeffreys
 *  
 * @param <V> Vertex type
 * @param <E> Edge type
 */



public interface Path<V,E extends Edge<V>> {
	
	
	/** Returns list of edges E that represents the walk/path from the source vertex to the target vertex.
	 * 
	 * @return list of edges.
	 */
	
	public List<E> getEdgeList();
	
	/** Returns the source vertex for the path.
	 *  
	 * @return source
	 */
	
	public V getSource();
	
	
	/** Returns the sink vertex for the path.
	 *  
	 * @return sink
	 */
	
	public V getSink();
	
	/** 
	 * @param i returns the vertex at location i, or null if i is outside of the vertex parameter.
	 * @return V vertex.
	 */
	
	public V getVertexAt(int i);
	
	/**Returns number of vertices in the path.
	 * 
	 * @return
	 */
	
	public int getNumVertices();
}
