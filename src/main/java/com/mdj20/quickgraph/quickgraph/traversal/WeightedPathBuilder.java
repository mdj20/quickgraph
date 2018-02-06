package com.mdj20.quickgraph.quickgraph.traversal;


import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> BaseGraph type.
 * @param <V> Vertex type.
 * @param <E> Edge type.
 * @param <W> Weight type.
 */

public class WeightedPathBuilder<G extends WeightedGraph<V,E,W>, V, E extends WeightedEdge<V,W>, W extends Number> extends PathBuilder<G, V, E> {
	
	public WeightedPathBuilder(G graph, V source) {
		super(graph, source);
	}

}
