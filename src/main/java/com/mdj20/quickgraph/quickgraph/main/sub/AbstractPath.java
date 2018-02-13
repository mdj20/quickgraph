package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.List;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.Edge;

public class AbstractPath<G extends BaseGraph<V,E>, V, E extends Edge<V>> extends AbstractSubGraph<G, V, E> {

	protected AbstractPath(G baseGraph, Set<V> vertices) {
		super(baseGraph, vertices);
		// TODO Auto-generated constructor stub
	}

	
}
