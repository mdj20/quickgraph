package com.mdj20.quickgraph.quickgraph.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


	/**
	 * @author Matthew D. Jeffreys
	 * 
	 * Abstract base class for undirected graph hierarchy.
	 * <p>
	 *
	 *
	 *
	 * @param <V> Vertex Type
	 * @param <E> Edge type extends Edge
	 */


public abstract class AbstractAdjacencyListGraph<V,E extends Edge<V>> implements GraphParameters, Graph<V,E> {
	
	protected HashMap<V, HashSet<E>> graph;
	protected HashSet<E> edges;
	protected HashSet<E> edgeNullObject;
	protected GraphParameters graphParameters;
	
	protected AbstractAdjacencyListGraph(GraphParameters params){
		graphParameters = params;
		graph = new HashMap<V,HashSet<E>>();
		edges= new HashSet<E>();
		edgeNullObject = new HashSet<E>();
	}

	@Override
	public boolean addVertex(V vertex) {
		boolean ret = false;
		if(!graph.containsKey(vertex)) {
			graph.put(vertex, new HashSet<E>());
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean addEdge(E edge) {
		boolean ret = false;
		if(checkVertices(edge.getVertices().get(0),edge.getVertices().get(1)) && !edgeExist(edge)) {
			addEdgeToGraph(edge.getVertices().get(0),edge);
			addEdgeToGraph(edge.getVertices().get(1),edge);
			edges.add(edge);
			ret =true;
		}
		return ret;
	}

	@Override
	public void removeVertex(V vertex) {
		if(graph.containsKey(vertex)) {
			edges.removeAll(graph.get(vertex));	
			Set<E> connectingEdges = new HashSet<E>(this.getConnectingEdges(vertex));
			for(E e: connectingEdges) {
				removeEdgeFromGraph(e.getVertices().get(0),e);
				removeEdgeFromGraph(e.getVertices().get(1),e);
			}
		
			graph.remove(vertex);
		}
	}

	@Override
	public void removeEdge(E edge) {
		if(checkVertices(edge)) {
			removeEdgeFromGraph(edge.getVertices().get(0),edge);
			removeEdgeFromGraph(edge.getVertices().get(1),edge);
			edges.remove(edge);
		}
	}

	@Override
	public Set<V> getVertices() {
		return graph.keySet();
	}

	@Override
	public Set<E> getEdges() {
		Set<E> ret = new HashSet<E>();
		ret.addAll(edges);
		return ret;
	}

	@Override
	public Set<V> getAdjacentVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		for(Edge<V> e: graph.get(vertex)) {
			ret.add(e.getOpposingVertex(vertex));
		}
 		return ret;
	}

	@Override
	public Set<E> getConnectingEdges(V vertex) {
		return graph.get(vertex);
	}

	@Override
	public boolean addEdge(V vertex1, V vertex2) {
		boolean ret =false;
		if(checkVertices(vertex1,vertex2)){
			addEdge(createEdge(vertex1,vertex2));
			ret = true;
		}
		return ret;
	}

	
	/** 
	 * Factory method must be overridden in any graph implementing class that inherits from AbstractAdjacencyListGraph.java
	 * <p>
	 * Method defines and constructs the type of edge connecting the edges within the graph. i.e. a directed graph would overide and return an Object of DirectionalEdge type.  
	 * @param vertex1 first connecting vertex
	 * @param vertex2 second connecting vertex
	 * @return E extends Edge
	 */
	
	protected abstract E createEdge(V vertex1, V vertex2);

	
	// checks if an edge or it's reciprocal exits.
	private boolean edgeExist(E edge) {
		Edge<V> reciprical = new SimpleEdge<V>(edge.getVertices().get(1),edge.getVertices().get(0));
		return edges.contains(edge) || edges.contains(reciprical);
	}
	
	protected boolean checkVertices(E edge) {
		return checkVertices(edge.getVertices().get(0),edge.getVertices().get(1));
	}
	protected boolean checkVertices(V v, V v1) {
		return (graph.containsKey(v)&&graph.containsKey(v1));
	}
	protected boolean addEdgeToGraph(V vertex, E edge) {
		 return graph.get(vertex).add(edge);
	}
	protected boolean removeEdgeFromGraph(V vertex, E edge) {
		return graph.get(vertex).remove(edge);
	}

	
	@Override
	public Set<V> getOutgoingVertices(V vertex) {
		return getAdjacentVertices(vertex);
	}

	@Override
	public Set<V> getIncomingVertices(V vertex) {
		return getAdjacentVertices(vertex);
	}

	@Override
	public Set<E> getOutgoingEdges(V vertex) {
		return getConnectingEdges(vertex);
	}

	@Override
	public Set<E> getIncomingEdges(V vertex) {
		return getConnectingEdges(vertex);
	}
	
	@Override
	public boolean isWeighted() {
		return graphParameters.isWeighted();
	}

	@Override
	public boolean isDirected() {
		return graphParameters.isDirected();
	}
	
	
}

