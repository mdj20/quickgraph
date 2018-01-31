package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;

/**
 *  @author Matthew D. Jeffreys
 *  
 * @param <V> Vertex type
 * @param <E> Edge type
 */



public interface Path<V,E extends Edge<V>> {
	public List<E> getEdgeList();
	public V getSource();
	public V getSink();
	public V getVertexAt(int i);
}
