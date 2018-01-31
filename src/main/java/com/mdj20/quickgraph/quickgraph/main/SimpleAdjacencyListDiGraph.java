package com.mdj20.quickgraph.quickgraph.main;
import java.util.HashSet;
import java.util.Set;

public class SimpleAdjacencyListDiGraph<V> extends AbstractAdjacencyListDiGraph<V,DirectionalEdge<V>> implements DiGraph<V,DirectionalEdge<V>> {

	@Override
	public DirectionalEdge<V> createEdge(V vertex1, V vertex2) {
		return new SimpleDirectionalEdge<V>(vertex1,vertex2);
	}
}
