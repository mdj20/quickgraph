package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.DirectedEdge;
import com.mdj20.quickgraph.quickgraph.main.DiGraph;

public class SubUserDiGraph<V> extends AbstractSubGraph<DiGraph<V>,V,DirectedEdge<V>> implements DiGraph<V> {
	SubUserDiGraph(DiGraph<V> parentGraph, Set<V> vertices) {
		super(parentGraph, vertices);
	}
}
