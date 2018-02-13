	package com.mdj20.quickgraph.quickgraph.main;

public class WeightedAdjListGraph<V, W> extends AbstractWeightedAdjListGraph<V,WeightedEdge<V,W>,W> implements WeightedGraph<V,W> {

	public WeightedAdjListGraph() {
		super(GraphType.WEIGHTED);
	}

	@Override
	public WeightedEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new DefaultWeightedEdge<V,W>(vertex1,vertex2,defaultWeight);
	}

	@Override
	public DefaultWeightedEdge<V,W> addEdge(V vertex1, V vertex2, W weight) {
		DefaultWeightedEdge<V,W> edge = new DefaultWeightedEdge<V,W>(vertex1,vertex2,weight);
		if(super.addEdge(edge)){
			return edge;
		}
		else {
			return null;
		}
	}
	
	

}