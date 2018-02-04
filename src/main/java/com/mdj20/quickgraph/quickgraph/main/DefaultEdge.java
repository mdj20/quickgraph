package com.mdj20.quickgraph.quickgraph.main;

/** Concrete implementation of Edge.
 * 
 * @author Matthew D. Jeffreys
 * @param <V> Vertex type.
 */

public class DefaultEdge<V> extends AbstractEdge<V>{
	public DefaultEdge(V v0, V v1) {
		super(v0, v1);
	}
}
