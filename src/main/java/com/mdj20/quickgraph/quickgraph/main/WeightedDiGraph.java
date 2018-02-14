package com.mdj20.quickgraph.quickgraph.main;

/**Defines a weighted and directed graph.
 * 
 * {@inheritDoc}
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type.
 * @param <W> Weight type.
 */


public interface WeightedDiGraph<V, W> extends BaseWeightedGraph<V,WeightedDirectedEdge<V,W>,W>{

}
