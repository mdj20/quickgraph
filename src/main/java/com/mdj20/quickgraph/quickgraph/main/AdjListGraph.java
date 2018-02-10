package com.mdj20.quickgraph.quickgraph.main;

import java.util.Set;

/**
 * Concrete implementation of a non-directed (and non weighted) graph. DefaultEdge<V>   
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Type of Vertex
 */

public class AdjListGraph<V> extends AbstractAdjListGraph<V, Edge<V>> implements Graph<V> {
	
	public AdjListGraph() {
		super(GraphType.SIMPLE);
	}

	/**
	 *  Overridden method creates returns an edge compatible with AdjListGraph.java internal structure. 
	 */
	
	@Override
	public Edge<V> createEdge(V vertex1, V vertex2) {
		return new DefaultEdge<V>(vertex1, vertex2);
	}
	
	


}
