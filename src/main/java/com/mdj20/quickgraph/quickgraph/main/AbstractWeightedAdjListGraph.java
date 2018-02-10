package com.mdj20.quickgraph.quickgraph.main;

public abstract class AbstractWeightedAdjListGraph<V, E extends WeightedEdge<V,W>, W> extends AbstractAdjListGraph<V,E> implements BaseWeightedGraph<V,E,W>{

	protected W defaultWeight = null;
	
	protected AbstractWeightedAdjListGraph(GraphParameters params) {
		super(params);
	}

	@Override
	public W setDefaultWeight(W weight) {
		W temp = defaultWeight;
		defaultWeight = weight;
		return temp;
	}
	
	@Override
	public W getDefaultWeight(){
		return defaultWeight;
	}

}