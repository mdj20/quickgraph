package com.mdj20.quickgraph.quickgraph.main;

/**
 * Combines Directional<V> and Edge<V> 
 * 
 * @author Matthew D. Jeffreys 
 * @param <V> Vertex type
 */

public interface DirectionalEdge<V> extends Edge<V>, Directional<V>{}
