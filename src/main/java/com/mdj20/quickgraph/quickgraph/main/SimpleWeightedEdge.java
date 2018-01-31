package com.mdj20.quickgraph.quickgraph.main;

public class SimpleWeightedEdge<V,W> extends AbstractEdge<V> implements WeightedEdge<V,W>, Edge<V>, Weighted<W> {

	private W weight;
	
	SimpleWeightedEdge(V v0, V v1) {
		super(v0, v1);
		// TODO Auto-generated constructor stub
	}
	SimpleWeightedEdge(V v0, V v1, W weight){
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
