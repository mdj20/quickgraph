package com.mdj20.quickgraph.quickgraph.main;

/**
 * Standard implementation of a directional edge.
 * 
 * Directional edge contains the methods getSource() and getSink(), which describe the direction/flow of the edge.
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Vertex type
 * 
 * 
 */

public class SimpleDirectionalEdge<V> extends SimpleEdge<V> implements DirectionalEdge<V>, Edge<V> {

	SimpleDirectionalEdge(V source, V sink) {
		super(source, sink);
	}

	@Override
	public V getSource() {
		return super.v.get(0);
	}

	@Override
	public V getSink() {
		return super.v.get(1);
	}
}
