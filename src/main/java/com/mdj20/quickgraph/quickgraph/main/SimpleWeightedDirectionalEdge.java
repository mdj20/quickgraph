package com.mdj20.quickgraph.quickgraph.main;

public class SimpleWeightedDirectionalEdge<V, W> extends SimpleDirectionalEdge<V> implements WeightedDirectionalEdge<V,W> {

	private W weight;
	
	SimpleWeightedDirectionalEdge(V source, V sink){
		this(source, sink, null);
	}
	
	SimpleWeightedDirectionalEdge(V source, V sink, W weight) {
		super(source, sink);
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
