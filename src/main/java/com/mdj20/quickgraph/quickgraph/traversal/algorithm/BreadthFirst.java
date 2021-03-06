package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;
import com.mdj20.quickgraph.quickgraph.testutilities.TestGraphData;

/** Contains static methods related to the breadth first search algorithm.
 * 
 * @author Matthew D. Jeffreys
 *
 */

public class BreadthFirst {
	
	
	/** Finds a path between two vertices via a breadth first search algorithm. Returns null if there is a path isn't found. 
	 * 
	 * @param graph the graph to be traversed 
	 * @param source the source vertex
	 * @param sink the target vertex
	 * @return list of edges, an aggregation representing a path from the source to the sink/target vertex.
	 */
	
	
	public static <V,E extends Edge<V>> List<E> breadthFirstSearch(BaseGraph<V,E> graph, V source, V sink){
		
		ArrayList<E> ret = new ArrayList<E>();
		LinkedList<SimplePathChain<V,E,?>> fifo = new LinkedList<SimplePathChain<V,E,?>>();
		HashMap<V,SimplePathChain<V,E,?>> metaMap = new HashMap<V,SimplePathChain<V,E,?>>();
		HashSet<V> checked = new HashSet<V>();
		boolean notFound = true;
		
		if( graph.getVertices().contains(source) && graph.getVertices().contains(sink) ){
			
			fifo.addLast(new SimplePathChain<V,E,Integer>(source,null,null));
			metaMap.put(source, fifo.peekFirst());
			
			while(notFound && !fifo.isEmpty() ){
				SimplePathChain<V,E,?> current = fifo.pollFirst();		
				checked.add(current.vertex);
				for(E edge : graph.getOutgoingEdges(current.vertex) ){
					V child = edge.getOpposingVertex(current.vertex);
					if(child.equals(sink)){
						notFound = false;
						SimplePathChain<V,E,Integer> childPathChain = new SimplePathChain<V,E,Integer>(child,edge,null);
						metaMap.put(child, childPathChain);
						break;
					}
					else if (!checked.contains(edge.getOpposingVertex(current.vertex))){
						SimplePathChain<V,E,Integer> childPathChain = new SimplePathChain<V,E,Integer>(child,edge,null);
						metaMap.put(child, childPathChain);
						fifo.addLast(childPathChain);
					}
				}
			}
			
			if(!notFound) { // if sink was found.
				ret = buildPathFromMeta(metaMap,source,sink);
				Collections.reverse(ret);
			}
			
		}
		
		return ret;
	};
	
	private static <V,E extends Edge<V>> ArrayList<E> buildPathFromMeta(HashMap<V,SimplePathChain<V,E,?>> metaMap , V source, V sink){
		LinkedList<E> buildList = new LinkedList<E>();
		ArrayList<E> ret = new ArrayList<E>(metaMap.keySet().size());
		SimplePathChain<V,E,?> current = metaMap.get(sink);
		V forward = current.vertex;
		if(!forward.equals(source)){
			E currentEdge = current.edge;
			V back = current.edge.getOpposingVertex(forward);
			buildList.addLast(currentEdge);
			while(!back.equals(source)){
				current = metaMap.get(back);
				currentEdge = current.edge;
				forward = current.vertex;
				back = current.edge.getOpposingVertex(forward);
				buildList.addLast(currentEdge);
			}
			ret.addAll(buildList);
		}
		return ret;
	}
}
