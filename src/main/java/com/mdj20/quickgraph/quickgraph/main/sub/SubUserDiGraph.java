package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.DirectedEdge;
import com.mdj20.quickgraph.quickgraph.main.UserDiGraph;

public class SubUserDiGraph<V> extends AbstractSubGraph<UserDiGraph<V>,V,DirectedEdge<V>> implements UserDiGraph<V> {
	SubUserDiGraph(UserDiGraph<V> parentGraph, Set<V> vertices) {
		super(parentGraph, vertices);
	}
}
