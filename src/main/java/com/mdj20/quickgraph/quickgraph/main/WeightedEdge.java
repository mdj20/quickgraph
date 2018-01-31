package com.mdj20.quickgraph.quickgraph.main;

/**
 * Interface defines a weighted edge implementation Combines Weighted<W>, Edge<V>
 *  
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type
 * @param <W> Weight type
 */

public interface WeightedEdge<V, W> extends Weighted<W>, Edge<V> {}
