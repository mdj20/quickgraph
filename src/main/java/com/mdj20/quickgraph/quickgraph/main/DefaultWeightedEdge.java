package com.mdj20.quickgraph.quickgraph.main;

/**
 * Concrete implementation of WeightedEdge
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type
 * @param <W> Weight Type
 */

public class DefaultWeightedEdge<V,W> extends DefaultEdge<V> implements WeightedEdge<V,W>, Edge<V>, Weighted<W> {

	private W weight;
	
	DefaultWeightedEdge(V v0, V v1) {
		super(v0, v1);
	}
	public DefaultWeightedEdge(V v0, V v1, W weight){
		this(v0,v1);
		this.weight = weight;
	}

	@Override
	public W getWeight() {
		return weight;
	}

	@Override
	public void setWeight(W weight) {
		this.weight = weight;
	}

}
