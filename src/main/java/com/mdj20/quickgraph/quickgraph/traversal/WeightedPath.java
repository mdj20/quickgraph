package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;



/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type
 * @param <W> Weight Type
 */

public interface WeightedPath<V,W> extends Path<V,WeightedEdge<V,W>> {
	public List<W> getWeights();
}
