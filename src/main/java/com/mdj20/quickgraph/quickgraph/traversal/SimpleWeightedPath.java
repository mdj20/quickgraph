package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.ArrayList;
import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;


/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type.
 * @param <W> Weight type.
 */

public class SimpleWeightedPath<V, W> extends SimplePath<V,WeightedEdge<V,W>> implements WeightedPath<V,W> {
	public SimpleWeightedPath(V source, V sink, List<WeightedEdge<V, W>> edgeList) {
		super(source, sink, edgeList);
	}

	@Override
	public List<W> getWeights() {
		ArrayList<W> ret = new ArrayList<W>();
		for(WeightedEdge<V,W> edge:edgeList){
			ret.add(edge.getWeight());
		}
		return ret;
	}
}
