package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.UserWeightedGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;

public class SubUserWeightedGraph<V,W> extends AbstractSubGraph<UserWeightedGraph<V,W>, V, WeightedEdge<V,W>> 
implements UserWeightedGraph<V,W> {

	protected SubUserWeightedGraph(UserWeightedGraph<V, W> baseGraph, Set<V> vertices) {
		super(baseGraph, vertices);
	}

	@Override
	public boolean addEdge(V vertex1, V vertex2, W weight) {
		boolean  ret = false; 
		if(checkVertices(vertex1,vertex2)) {
			ret = parentGraph.addEdge(vertex1, vertex2, weight);
		}
		return ret;
	}

}
