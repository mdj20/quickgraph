package com.mdj20.quickgraph.quickgraph.traversal;
import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;
import com.mdj20.quickgraph.quickgraph.traversal.algorithm.BreadthFirst;
import com.mdj20.quickgraph.quickgraph.traversal.algorithm.DepthFirst;

public abstract class AbstractPathFinder<G extends Graph<V,E>,V,E extends Edge<V>> {
	protected G graph;

	protected AbstractPathFinder(G graph) {
		if(graph == null) {
			throw new IllegalArgumentException("Argument graph cannot be null");
		}
		this.graph = graph;
	}
	
	/** Returns a list of edges that represents a walk from the source vertex to the sink vertex, as determined via a depth
	 * first search.
	 * 
	 * @param source the starting vertex
	 * @param sink the target vertex
	 * @return aggregate list of edges that represent the path, or null of no path is found.
	 */
	
	
	public List<E> depthFirstEdgeList(V source, V sink){
		return DepthFirst.depthFirstSearch(graph, source, sink);
	}
	
	/**
	 * Returns a list of edges that represents a walk from the source vertex to the sink vertex, as determined via a breadth
	 * first search.
	 * 
	 * @param source starting vertex
	 * @param sink target vertex
	 * @return aggregate list of edges that represent the path, or null of no path is found.
	 */
	
	public List<E> breadthFirstEdgeList(V source, V sink){
		return BreadthFirst.breadthFirstSearch(graph, source, sink);
	}
	
	/**
	 * Returns a Path object  that represents a walk from the source vertex to the sink vertex, as determined via a depth
	 * first search.
	 * 
	 * @param source starting vertex
	 * @param sink target vertex
	 * @return Path object or null if no path is found.
	 */
	
	public Path<V,E> depthFirstPath(V source, V sink){
		Path<V,E> ret = null;
		PathBuilder<G,V,E> pBuilder = new PathBuilder<G,V,E>(graph,source);
		List<E> edgeList =  depthFirstEdgeList(source,sink);
		if(edgeList != null) {
			pBuilder.addEdges(edgeList);
			ret = pBuilder.build();
		}
		return ret;
	}
	
	/**
	 * Returns a Path object  that represents a walk from the source vertex to the sink vertex, as determined via a breadth
	 * first search.
	 * 
	 * @param source starting vertex
	 * @param sink target vertex
	 * @return Path object or null if no path is found.
	 */
	
	public Path<V,E> breadthFirstPath(V source, V sink){
		Path<V,E> ret = null;
		PathBuilder<G,V,E> pBuilder = new PathBuilder<G,V,E>(graph,source);
		List<E> edgeList =  breadthFirstEdgeList(source,sink);
		if(edgeList != null) {
			pBuilder.addEdges(edgeList);
			ret = pBuilder.build();
		}
		return ret;
	}
	
	/**
	 * Returns the graph that was used to initialize the pathfinder.
	 * 
	 * @return
	 */
	
	public G getGraph() {
		return graph;
	}
	
}
