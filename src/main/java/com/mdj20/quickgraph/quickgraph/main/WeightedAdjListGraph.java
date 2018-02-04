	package com.mdj20.quickgraph.quickgraph.main;

public class WeightedAdjListGraph<V, W> extends AbstractAdjListGraph<V,WeightedEdge<V,W>> implements WeightedGraph<V,WeightedEdge<V,W>,W> {

	public WeightedAdjListGraph() {
		super(GraphType.WEIGHTED);
	}

	@Override
	public WeightedEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new DefaultWeightedEdge<V,W>(vertex1,vertex2);
	}

	@Override
	public boolean addEdge(V vertex1, V vertex2, W weight) {
		WeightedEdge<V,W> edge = new DefaultWeightedEdge<V,W>(vertex1,vertex2,weight);
		return super.addEdge(edge);
	}
	
	

}