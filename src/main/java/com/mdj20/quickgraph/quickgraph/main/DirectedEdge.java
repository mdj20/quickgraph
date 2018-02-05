package com.mdj20.quickgraph.quickgraph.main;

/**
 * Combines Directed<V> and Edge<V> 
 * 
 * @author Matthew D. Jeffreys 
 * @param <V> Vertex type
 */

public interface DirectedEdge<V> extends Edge<V>, Directed<V>{}
