package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;


/** Abstract base class for the Immutable Sub Graph hierarchy.
 * 
 * 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> Graph type.
 * @param <V> Vertex type.
 * @param <E> Edge type.
 */

public abstract class AbstractImmutableSubGraph<G extends BaseGraph<V,E>,V,E extends Edge<V>> extends AbstractSubGraph<G,V,E> {

	AbstractImmutableSubGraph(G parentGraph, Set<V> vertices) {
		super(parentGraph, vertices);
	}

	
	@Override
	public boolean addVertex(V vertex) {
		throw new UnsupportedOperationException("SubGraph is Immutable");
	}
	

	@Override
	public boolean addEdge(E edge) {
		throw new UnsupportedOperationException("SubGraph is Immutable");
	}

	@Override
	public E addEdge(V vertex1, V vertex2) {
		throw new UnsupportedOperationException("SubGraph is Immutable");
	}

	
	@Override
	public void removeVertex(V vertex) {
		throw new UnsupportedOperationException("SubGraph is Immutable");
	}

	@Override
	public void removeEdge(E edge) {
		throw new UnsupportedOperationException("SubGraph is Immutable");
	}
	
}
