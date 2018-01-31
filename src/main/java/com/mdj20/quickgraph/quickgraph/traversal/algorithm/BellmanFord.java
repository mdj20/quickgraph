package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;


/**
 * Contains static methods related to the Bellman-Ford path/distance finding methods.
 * 
 * @author Matthew D. Jeffreys
 *
 */


public class BellmanFord {
	
	/**
	 * 
	 * @param graph
	 * @param source
	 * @return 
	 */

	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> Map<V,Integer> findDistancesInt(WeightedGraph<V,E,W> graph, V source){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		int nVertex = vertices.size();
		HashMap<V,Integer>  distanceMap = new HashMap<V,Integer>();
		distanceMap.put(source, 0);
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(WeightedEdge<V,W> edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v)+edge.getWeight().intValue() < distanceMap.get(opposing)){
								distanceMap.put(opposing, distanceMap.get(v).intValue() + edge.getWeight().intValue() );
							}	
						}
						else {
							distanceMap.put(opposing, edge.getWeight().intValue()+distanceMap.get(v));
						}
					}
				}
			}
		}
		return distanceMap;
	}
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> Map<V,Double> findDistancesDouble(WeightedGraph<V,E,W> graph, V source){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		int nVertex = vertices.size();
		HashMap<V,Double>  distanceMap = new HashMap<V,Double>();
		distanceMap.put(source, 0.0);
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(WeightedEdge<V,W> edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v)+edge.getWeight().intValue() < distanceMap.get(opposing)){
								distanceMap.put(opposing, distanceMap.get(v).doubleValue() + edge.getWeight().doubleValue() );
							}	
						}
						else {
							distanceMap.put(opposing, edge.getWeight().doubleValue()+distanceMap.get(v));
						}
					}
				}
			}
		}
		return distanceMap;
	}
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathInt(G graph, V source,V sink){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		ArrayList<E> pathEdgeList = new ArrayList<E>(); 
		int nVertex = vertices.size();
		HashMap<V,WeightedPathChain<V,E,W,Integer>>  distanceMap = new HashMap<V,WeightedPathChain<V,E,W,Integer>>();
		distanceMap.put(source, new WeightedPathChain<V,E,W,Integer>(source,0));
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(E edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v).getVal()+edge.getWeight().intValue() < distanceMap.get(opposing).getVal()){
								
								WeightedPathChain<V,E,W,Integer> pathChain =distanceMap.get(opposing);
								pathChain.setValue(distanceMap.get(v).getVal() + edge.getWeight().intValue());
								pathChain.setEdge(edge);
							}	
						}
						else {
							distanceMap.put(opposing,
									new WeightedPathChain<V,E,W,Integer>(opposing,
									edge,
									edge.getWeight().intValue()+distanceMap.get(v).getVal())) ;
						}
					}
				}
			}
		}
		
		// construct pathEdgeList
		if(distanceMap.containsKey(sink)){
			V current = sink;
			E edge = distanceMap.get(current).edge;
			pathEdgeList.add(edge);
			V opposing = edge.getOpposingVertex(current);
			while (!opposing.equals(source)){
				current = opposing;
				edge = distanceMap.get(current).edge;
				pathEdgeList.add(edge);
				opposing = edge.getOpposingVertex(current);
			}
		}
	
		// reverse edgeList
		Collections.reverse(pathEdgeList);
		return pathEdgeList;
	}
	
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathDouble(G graph, V source,V sink){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		ArrayList<E> pathEdgeList = new ArrayList<E>(); 
		int nVertex = vertices.size();
		HashMap<V,WeightedPathChain<V,E,W,Double>>  distanceMap = new HashMap<V,WeightedPathChain<V,E,W,Double>>();
		distanceMap.put(source, new WeightedPathChain<V,E,W,Double>(source,0.0));
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(E edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v).getVal()+edge.getWeight().doubleValue() < distanceMap.get(opposing).getVal()){
								WeightedPathChain<V,E,W,Double> pathChain = distanceMap.get(opposing);
								pathChain.setValue(distanceMap.get(v).getVal() + edge.getWeight().doubleValue());
								pathChain.setEdge(edge);
							}	
						}
						else {
							distanceMap.put(opposing,
									new WeightedPathChain<V,E,W,Double>(opposing,
									edge,
									edge.getWeight().doubleValue()+distanceMap.get(v).getVal())) ;
						}
					}
				}
			}
		}
		
		// construct pathEdgeList
		if(distanceMap.containsKey(sink)){
			V current = sink;
			E edge = distanceMap.get(current).edge;
			pathEdgeList.add(edge);
			V opposing = edge.getOpposingVertex(current);
			while (!opposing.equals(source)){
				current = opposing;
				edge = distanceMap.get(current).edge;
				pathEdgeList.add(edge);
				opposing = edge.getOpposingVertex(current);
			}
		}
	
		// reverse edgeList
		
		Collections.reverse(pathEdgeList);
		return pathEdgeList;
	}
	
}
