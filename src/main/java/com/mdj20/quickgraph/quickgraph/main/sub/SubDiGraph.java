package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.DirectedEdge;
import com.mdj20.quickgraph.quickgraph.main.DiGraph;

public class SubDiGraph<V> extends AbstractSubGraph<DiGraph<V>,V,DirectedEdge<V>> implements DiGraph<V> {
	SubDiGraph(DiGraph<V> parentGraph, Set<V> vertices) {
		super(parentGraph, vertices);
	}
}
