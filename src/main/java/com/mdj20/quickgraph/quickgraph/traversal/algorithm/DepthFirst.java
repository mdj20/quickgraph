package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;
import com.mdj20.quickgraph.quickgraph.testutilities.TestGraphData;

/** Contains static methods relating to the depth fist search algorithm.
 *  
 * @author Matthew D. Jeffreys
 *
 */

public class DepthFirst {
	
	/**
	 * 
	 * @param graph the graph to be traversed
	 * @param source the starting/ source vertex
	 * @param sink the target/sink vertex
	 * @return list of edges, an aggregation representing a path from the source to the sink/target vertex.
	 */

	public static <V,E extends Edge<V>> List<E> depthFirstSearch(BaseGraph<V,E> graph, V source, V sink){
		List<E> ret = new ArrayList<E>();
		if(graph.getVertices().contains(source)&&graph.getVertices().contains(sink)) {
			LinkedList<SimplePathChain<V,E,?>> pathChain = new LinkedList<SimplePathChain<V,E,?>>();
			HashSet<V> checked = new HashSet<V>();
			pathChain.add(new SimplePathChain<V,E,Integer>(source,null,null));
			if(DepthFirstRecursive(graph,source,sink,pathChain,checked)){	
				ret =  aggregateEdgeList(pathChain);
				for(E e: ret){
					//System.out.println(e.getVertex(0)+" "+e.getVertex(1));
				}
			}
		}
		return ret;
	}
	
	
	private static <V,E extends Edge<V>> boolean 
	DepthFirstRecursive(BaseGraph<V,E> graph, V source, V sink, LinkedList<SimplePathChain<V,E,?>> pathChain, HashSet<V> checked ) {
		boolean ret = false;
		if(pathChain.peekLast()!=null) {
			V current =  pathChain.peekLast().vertex;
			checked.add(current);
			Set<E> nextEdges = graph.getOutgoingEdges(current);
			for(E e : nextEdges) {
				if(!checked.contains(e.getOpposingVertex(current))) {
					if(e.getOpposingVertex(current).equals(sink)) {
						ret = true; 
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null)); // Note integer isn't used for anything in this context
						break;
					}
					else {
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null));
						if(DepthFirstRecursive(graph,source,sink,pathChain,checked)) {
							ret = true;
							break;
						}
						else {
							pathChain.pollLast();
						}
					}
				}
			}
			checked.remove(current);
		}
		return ret;	
	}
	
	private static <V,E extends Edge<V>> ArrayList<E> aggregateEdgeList(LinkedList<SimplePathChain<V,E,?>> pathChain ){
		ArrayList<E> edgeList = new ArrayList<E>(pathChain.size());
		pathChain.pollFirst();
		for(SimplePathChain<V,E,?> pc: pathChain){
			edgeList.add(pc.edge);
		}
		return edgeList;
	}
}
