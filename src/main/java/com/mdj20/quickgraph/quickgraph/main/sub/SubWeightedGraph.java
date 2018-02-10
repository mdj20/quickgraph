package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.BaseWeightedGraph;

public class SubWeightedGraph<V,W> extends AbstractSubGraph<WeightedGraph<V,W>, V, WeightedEdge<V,W>> 
implements WeightedGraph<V,W> {
	
	protected W subDefaultWeight;

	protected SubWeightedGraph(WeightedGraph<V, W> baseGraph, Set<V> vertices) {
		super(baseGraph, vertices);
		subDefaultWeight = baseGraph.getDefaultWeight();
	}

	
	@Override
	public WeightedEdge<V, W> addEdge(V vertex1, V vertex2, W weight) {
		WeightedEdge<V, W>  ret = null;
		if(checkVertices(vertex1,vertex2)) {
			ret = parentGraph.addEdge(vertex1, vertex2, weight);
		}
		return ret;
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

	@Override
	public WeightedEdge<V, W> addEdge(V vertex1, V vertex2) {
		return addEdge(vertex1,vertex2,subDefaultWeight);
	}
	
	

}
