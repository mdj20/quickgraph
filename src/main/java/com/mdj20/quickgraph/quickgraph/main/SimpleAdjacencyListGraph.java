package com.mdj20.quickgraph.quickgraph.main;

import java.util.Set;

/**
 * Basic concrete implementation of a non-directed (and non weighted) graph. SimpleEdge<V>   
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Type of Vertex
 */

public class SimpleAdjacencyListGraph<V> extends AbstractAdjacencyListGraph<V, SimpleEdge<V>> {
	
	public SimpleAdjacencyListGraph() {
		super(GraphType.WEIGHTED_DIRECTED);
	}

	
	/**
	 *  Overridden method creates returns an edge compatible with SimpleAdjacencyListGraph.java internal struction 
	 */
	
	@Override
	public SimpleEdge<V> createEdge(V vertex1, V vertex2) {
		return new SimpleEdge<V>(vertex1, vertex2);
	}

}
