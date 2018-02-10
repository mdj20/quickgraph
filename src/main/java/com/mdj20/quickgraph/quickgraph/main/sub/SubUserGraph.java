package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;

public class SubUserGraph<V> extends AbstractSubGraph<Graph<V>, V, Edge<V>> implements Graph<V> {
	SubUserGraph(Graph<V> parentGraph, Set<V> vertices){
		super(parentGraph,vertices);
	}
}
