package com.mdj20.quickgraph.quickgraph.main;

/**
 * Defines a weighted graph, implementation. 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type.
 * @param <W> Weight type.
 */

public interface WeightedGraph<V, W> extends BaseWeightedGraph<V, WeightedEdge<V,W>, W> {}
