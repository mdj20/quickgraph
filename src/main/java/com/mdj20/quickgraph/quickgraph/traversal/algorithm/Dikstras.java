package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;

import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

/**
 * Contains static methods related to the Bellman-Ford path/distance finding methods.
 * 
 * @author Matthew D. Jeffreys
 *
 */




public class Dikstras{
	
	/**	Finds the shortest path via the Dikstras algorithm, between 2 vertices. 
	 * The weight type in the graph must extend Number, and will calculate the weights as their Integer representation. 
	 * 
	 * 
	 * @param graph WeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting vertex.
	 * @param sink Target vertex.
	 * @return List<E extends Edge> list of edges in the order that represents a path, null if no path is found.
	 */
	
	

	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathInt(G graph, V source, V sink){
		// check graph contains source and sink
		Set<V> vertecies = graph.getVertices();
		if( !(vertecies.contains(source) && vertecies.contains(sink)) ){
			throw new IllegalArgumentException("Graph must contain both source and sink");
		}
		
		PriorityQueue<WeightedPathChain<V,E,W,Integer>> pq = new PriorityQueue<WeightedPathChain<V,E,W,Integer>>(); 
		HashSet<V> checked = new HashSet<V>();
		HashMap<V,WeightedPathChain<V,E,W,Integer>> map = new HashMap<V,WeightedPathChain<V,E,W,Integer>>();
		addOrUpdate(map,pq,source,Integer.valueOf(0));
		while(!pq.isEmpty()&&!sink.equals(pq.peek().vertex)){
			WeightedPathChain<V,E,W,Integer> current = pq.poll();  // poll lowest level vertex
			checked.add(current.vertex);  // add vertex to checked set
			for (E  edge  :graph.getOutgoingEdges(current.vertex)){	// for each outgoing edge 
				if(!checked.contains(edge.getOpposingVertex(current.vertex))){		// if opposing vertex has not been checked   
					addOrUpdate(map,
								pq,
								edge.getOpposingVertex(current.vertex),
								Integer.valueOf(map.get(current.vertex).val+edge.getWeight().intValue() ) ,
								edge);
				}
			}
		}
		ArrayList<V> path = new ArrayList<V>();  // vertex path 
		ArrayList<E> edgePath = new ArrayList<E>(); // edge path
		boolean run = true;  
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {  // 
			WeightedPathChain<V,E,W,Integer> vv = pq.poll();
			path.add(vv.vertex);
			edgePath.add(vv.edge);
			while(run) {
				vv = map.get(vv.edge.getOpposingVertex(vv.vertex));
				if(vv.vertex.equals(source)) {
					run=false;
					path.add(vv.vertex);
				}
				else {	
					edgePath.add(vv.edge);
		
				}
			}
		}
		//return path;
		
		Collections.reverse(edgePath);
		return edgePath;
	}
	
	/**	Finds the shortest path via the Dikstras algorithm, between 2 vertices. 
	 * The weight type in the graph must extend Number, and will calculate the weights as their Double representation. 
	 * 
	 * 
	 * @param graph WeightedGraph<?,WeightedEdge<V,W>> W weight must extends Number & Comparable<W>
	 * @param source Starting vertex.
	 * @param sink Target vertex.
	 * @return List<E extends Edge> list of edges in the order that represents a path, null if no path is found.
	 */
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathDouble(G graph, V source, V sink){
		// check graph contains source and sink
		Set<V> vertecies = graph.getVertices();
		if( !(vertecies.contains(source) && vertecies.contains(sink)) ){
			throw new IllegalArgumentException("Graph must contain both source and sink");
		}
		
		PriorityQueue<WeightedPathChain<V,E,W,Double>> pq = new PriorityQueue<WeightedPathChain<V,E,W,Double>>(); 
		HashSet<V> checked = new HashSet<V>();
		HashMap<V,WeightedPathChain<V,E,W,Double>> map = new HashMap<V,WeightedPathChain<V,E,W,Double>>();
		addOrUpdate(map,pq,source,Double.valueOf(0));
		while(!pq.isEmpty()&&!sink.equals(pq.peek().vertex)){
			WeightedPathChain<V,E,W,Double> current = pq.poll();  // poll lowest level vertex
			checked.add(current.vertex);  // add vertex to checked set
			for (E  edge  :graph.getOutgoingEdges(current.vertex)){	// for each outgoing edge 
				if(!checked.contains(edge.getOpposingVertex(current.vertex))){		// if opposing vertex has not been checked   
					addOrUpdate(map,
								pq,
								edge.getOpposingVertex(current.vertex),
								Double.valueOf(map.get(current.vertex).val+edge.getWeight().doubleValue() ) ,
								edge);
				}
			}
		}
		ArrayList<V> path = new ArrayList<V>();  // vertex path 
		ArrayList<E> edgePath = new ArrayList<E>(); // edge path
		boolean run = true;  
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {  // 
			WeightedPathChain<V,E,W,Double> vv = pq.poll();
			path.add(vv.vertex);
			edgePath.add(vv.edge);
			while(run) {
				vv = map.get(vv.edge.getOpposingVertex(vv.vertex));
				if(vv.vertex.equals(source)) {
					run=false;
					path.add(vv.vertex);
				}
				else {	
					edgePath.add(vv.edge);
		
				}
			}
		}
		//return path;
		
		Collections.reverse(edgePath);
		return edgePath;
	}
	
	
	private static <V, E extends WeightedEdge<V,W>, W extends Number & Comparable<W>,I extends Comparable<I>> WeightedPathChain<V,E,W,I> addOrUpdate(HashMap<V, WeightedPathChain<V,E,W,I>> discoverMap, PriorityQueue<WeightedPathChain<V,E,W,I>> pq, V vertex, I value, E edge){
		WeightedPathChain<V,E,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new WeightedPathChain<V,E,W,I>(vertex,edge,value));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			I temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value.compareTo(temp)<0){
				pq.remove(ret);
				ret.val = value;
				ret.edge= edge;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
	private static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>,I extends Comparable<I>> 
	WeightedPathChain<V,E,W,I> addOrUpdate(HashMap<V, WeightedPathChain<V,E,W,I>> discoverMap, PriorityQueue<WeightedPathChain<V,E,W,I>> pq, V vertex, I value){
		WeightedPathChain<V,E,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new WeightedPathChain<V,E,W,I>(vertex,value));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			I temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value.compareTo(temp)<0){
				pq.remove(ret);
				ret.val = value;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
}
