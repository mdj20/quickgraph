package com.mdj20.quickgraph.quickgraph.main;

import java.util.Set;

/**
 * Concrete implementation of a graph that is neither weighted or directed.    
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Type of Vertex
 */

public class AdjListGraph<V> extends AbstractAdjListGraph<V, Edge<V>> implements Graph<V> {
	
	public AdjListGraph() {
		super(GraphType.SIMPLE);
	}

	/**
	 *  Method creates and returns an Edge class that connects vertex1 and vertex2.
	 */
	
	@Override
	public Edge<V> createEdge(V vertex1, V vertex2) {
		return new DefaultEdge<V>(vertex1, vertex2);
	}
	
	


}
