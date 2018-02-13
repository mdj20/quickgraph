package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.WeightedDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectedEdge;

public class SubWeightedDiGraph<V, W> extends AbstractSubGraph<WeightedDiGraph<V,W>, V, WeightedDirectedEdge<V,W>> implements WeightedDiGraph<V,W> {

	protected W subDefaultWeight;
	
	protected SubWeightedDiGraph(WeightedDiGraph<V, W> baseGraph, Set<V> vertices) {
		super(baseGraph, vertices);
		subDefaultWeight = baseGraph.getDefaultWeight();
	}

	@Override
	public WeightedDirectedEdge<V, W> addEdge(V vertex1, V vertex2, W weight) {
		return parentGraph.addEdge(vertex1, vertex2, weight);
	}

	@Override
	public W setDefaultWeight(W weight) {
		W temp = subDefaultWeight;
		subDefaultWeight = weight;
		return temp;
	}

	@Override
	public W getDefaultWeight() {
		return subDefaultWeight;
	}

}
