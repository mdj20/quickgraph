package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectionalEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;
import com.mdj20.quickgraph.quickgraph.testutilities.TestGraphData;

public class DepthFirst {

	public static <V,E extends Edge<V>> List<E> depthFirstSearch(Graph<V,E> graph, V source, V sink){
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
	DepthFirstRecursive(Graph<V,E> graph, V source, V sink, LinkedList<SimplePathChain<V,E,?>> pathChain, HashSet<V> checked ) {
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
	
	
	public static void main(String args[]){
		// smoke test
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph0);
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < 25000 ; i++){
			WeightedAdjacencyListDiGraph<Character,Integer> rGraph = FastGraphBuilder.buildRandomWeightedDiGraph(25, 45, 1, 15);
			Character source = null, sink = null;
			ArrayList<Character> verts =  new ArrayList<Character>(rGraph.getVertices());
			while(source == null || sink == null || source.equals(sink)){
				source = verts.get(rando.nextInt(25));
				sink = verts.get(rando.nextInt(25));
			}
			List<WeightedDirectionalEdge<Character,Integer>> edgeList = depthFirstSearch(rGraph,source,sink);
			System.out.println("GRAPH :"+source+" -> "+sink+"\nEdge: "+edgeList.size());
			for(WeightedDirectionalEdge<Character,Integer> wde: edgeList){
				System.out.println(wde.getSource()+" "+wde.getSink());
			}
			System.out.println();
		}
	}
}
