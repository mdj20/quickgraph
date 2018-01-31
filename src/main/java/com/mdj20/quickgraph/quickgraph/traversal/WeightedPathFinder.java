package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;
import com.mdj20.quickgraph.quickgraph.traversal.algorithm.BellmanFord;
import com.mdj20.quickgraph.quickgraph.traversal.algorithm.Dikstras;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> Graph type.
 * @param <E> Edge type.
 * @param <V> Vertex type.
 * @param <W> Weight type.
 */

public class WeightedPathFinder<G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W extends Number & Comparable<W>>
extends AbstractPathFinder<G, V, E> {
	
	protected WeightedPathFinder(G graph) {
		super(graph);
	}
	
	// factory method infers type parameters, and returns appropriate object
	public static <G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W extends Number & Comparable<W>> WeightedPathFinder<G, E, V, W> getWeightedPathFinder(G graph){
		return new WeightedPathFinder<G,E,V,W>(graph);
	}
	
	
	// bellmanford int 
	public Path<V,E> bellmanFordIntPath(V source, V sink){
		Path<V,E> ret = null;
		List<E> edgeList = BellmanFord.findShortestPathInt(super.getGraph(), source, sink);
		WeightedPathBuilder<G,V,E,W> weightedPathBuilder = new WeightedPathBuilder<G,V,E,W>(graph,source);
		if(edgeList!=null&& !edgeList.isEmpty()) {
			for(E e: edgeList) {
				weightedPathBuilder.addEdge(e);
			}
		}
		ret = weightedPathBuilder.build();
		return ret;
	}
	
	// bellmanford double
	public Path<V,E> bellmanFordDoublePath(V source, V sink){
		Path<V,E> ret = null;
		List<E> edgeList = BellmanFord.findShortestPathDouble(super.getGraph(), source, sink);
		WeightedPathBuilder<G,V,E,W> weightedPathBuilder = new WeightedPathBuilder<G,V,E,W>(graph,source);
		if(edgeList!=null&& !edgeList.isEmpty()) {
			for(E e: edgeList) {
				weightedPathBuilder.addEdge(e);
			}
		}
		ret = weightedPathBuilder.build();
		return ret;
	}
	
	
	// dikstras int 
	public Path<V,E> dikstrasIntPath(V source, V sink){
		Path<V,E> ret = null;
		List<E> edgeList = Dikstras.findShortestPathInt(super.getGraph(), source, sink);
		WeightedPathBuilder<G,V,E,W> weightedPathBuilder = new WeightedPathBuilder<G,V,E,W>(graph,source);
		if(edgeList!=null) {
			for(E e: edgeList) {
				weightedPathBuilder.addEdge(e);
			}
		}
		ret = weightedPathBuilder.build();
		return ret;
	}
	
	// dikstras double
	
	
}
