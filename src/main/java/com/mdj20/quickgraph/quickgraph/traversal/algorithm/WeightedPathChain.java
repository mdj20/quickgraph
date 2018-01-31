package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;

public class WeightedPathChain<V, E extends WeightedEdge<V,W>,W, I extends  Comparable<I>> extends SimplePathChain<V, E, I> {
	WeightedPathChain(V vertex, E edge, I intermediate) {
		super(vertex, edge, intermediate);
	}

	public WeightedPathChain(V vertex, I value) {
		this(vertex, null , value);
	}
}
