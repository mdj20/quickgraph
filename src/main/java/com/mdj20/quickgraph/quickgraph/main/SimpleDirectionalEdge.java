package com.mdj20.quickgraph.quickgraph.main;

/**
 * Standard implementation of a directional edge.
 * 
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Vertex type
 * 
 * 
 */

public class SimpleDirectionalEdge<V> extends AbstractEdge<V> implements DirectionalEdge<V>, Edge<V> {

	SimpleDirectionalEdge(V source, V sink) {
		super(source, sink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public V getSource() {
		return super.v.get(0);
	}

	@Override
	public V getSink() {
		// TODO Auto-generated method stub
		return super.v.get(1);
	}
}
