package com.mdj20.quickgraph.quickgraph.traversal;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;


public class SimplePathFinder<V,E extends Edge<V>> extends AbstractPathFinder<BaseGraph<V,E>, V, E> {

	protected SimplePathFinder(BaseGraph<V, E> graph) {
		super(graph);
	}

	
	public static <G extends BaseGraph<V,E>, E extends WeightedEdge<V,W>, V, W extends Number & Comparable<W>> SimplePathFinder<V, E> getSimplePathFinder(G graph){
		return new SimplePathFinder<V,E>(graph);
	}
	
	
}
