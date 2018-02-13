package com.mdj20.quickgraph.quickgraph.main;
import java.util.Set;

/**
 * 	A directed graph.
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type
 * @param <E> Edge Type extends DirectedEdge<V>
 */

public interface BaseDiGraph<V,E extends DirectedEdge<V>> extends BaseGraph<V,E> {
	public Set<V> getOutgoingVertices(V vertex);
	public Set<V> getIncomingVertices(V vertex);
	public Set<E> getOutgoingEdges(V vertex);
	public Set<E> getIncomingEdges(V vertex);
}
