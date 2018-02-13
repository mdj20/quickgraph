package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.BaseWeightedGraph;


/**
 * Contains static methods related to the Bellman-Ford path/distance finding methods.
 * 
 * @author Matthew D. Jeffreys
 *
 */


public class BellmanFord {
	
	/** Finds the distance to from the argument vertex to all vertices that are connected to the source vertex.
	 *<p>
	 * This will work for any type of weight that extends number. The algorithm will treat every weight as it's integer representation.
	 * 
	 * @param graph BaseWeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting/source vertex
	 * @return Map<V,Integer> vertices mapped to their respective distances
	 */

	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> Map<V,Integer> findDistancesInt(BaseWeightedGraph<V,E,W> graph, V source){

		
		
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
	
	/** Finds the distance to from the argument vertex to all vertices that are connected to the source vertex.
	 *<p>
	 * The weight type in the graph must extend Number, The algorithm will treat every weight as it's double representation.
	 * 
	 * @param graph BaseWeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting vertex
	 * @return Map<V,Double> vertex mapped to their respective distances
	 */
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> Map<V,Double> findDistancesDouble(BaseWeightedGraph<V,E,W> graph, V source){
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
	
	/**	Finds the shortest path via the Bellman-Ford algorithm, between 2 vertices. 
	 * The weight type in the graph must extend Number, and will calculate the weights as their Integer representation. 
	 * 
	 * 
	 * @param graph BaseWeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting vertex.
	 * @param sink Target vertex.
	 * @return List<E extends Edge> list of edges in the order that represents a path, null if no path is found.
	 */
	
	public static <G extends BaseWeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathInt(G graph, V source,V sink){
		checkArgument(graph, source, sink); // throws IllegalArgumentException
		
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
	
	/**	Finds the shortest path via the Bellman-Ford algorithm, between 2 vertices. 
	 * The weight type in the graph must extend Number, and will calculate the weights as their Double representation. 
	 * 
	 * 
	 * @param graph BaseWeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting vertex.
	 * @param sink Target vertex.
	 * @return List<E extends Edge> list of edges in the order that represents a path, null if no path is found.
	 */
	
	public static <G extends BaseWeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathDouble(G graph, V source,V sink){
		checkArgument(graph, source, sink); // throws IllegalArgumentException
		
		
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
	
	
	
	private static <V> void checkSS(V source, V sink){
		if (source.equals(sink))
			throw new IllegalArgumentException("source and sink must not be equal");
	}
	private static <G extends BaseWeightedGraph<V,?,?>,V> void checkVerts(G graph, V source, V sink){
		if( ! (graph.getVertices().contains(source) && graph.getVertices().contains(sink)) )
				throw new IllegalArgumentException("source and sink must be vertices in graph");
	}
	private static void checkNull(Object o1, Object o2){
		if(o1==null || o2 == null){
			throw new IllegalArgumentException("Argument can't be null");
		}
	}
	private static <G extends BaseWeightedGraph<V,?,?>,V> void checkArgument(G graph, V source, V sink){
		checkSS(source,sink);
		checkVerts(graph,source,sink);
		checkNull(source,sink);
	}
	
}
