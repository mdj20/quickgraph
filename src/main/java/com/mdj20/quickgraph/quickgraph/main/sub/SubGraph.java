package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;

public class SubGraph<V> extends AbstractSubGraph<Graph<V>, V, Edge<V>> implements Graph<V> {
	SubGraph(Graph<V> parentGraph, Set<V> vertices){
		super(parentGraph,vertices);
	}
}
