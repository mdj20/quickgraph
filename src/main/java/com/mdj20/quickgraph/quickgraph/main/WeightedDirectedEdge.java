package com.mdj20.quickgraph.quickgraph.main;
/**
 * Combines WeightedEdge<V,E>, DirectedEdge<V> describes the edge object used to build WeightedDiGraph.
 * 
 * 
 * @author Matthew D. Jeffreys
 * 
 * @param <V> Vertex type
 * @param <W> Weight type
 */
public interface WeightedDirectedEdge<V,W> extends WeightedEdge<V,W>, DirectedEdge<V> {}
