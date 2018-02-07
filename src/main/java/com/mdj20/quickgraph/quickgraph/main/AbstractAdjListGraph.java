package com.mdj20.quickgraph.quickgraph.main;

import java.util.HashMap;
import java.util.HashSet;
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


public abstract class AbstractAdjListGraph<V,E extends Edge<V>> implements GraphParameters, BaseGraph<V,E> {
	
	protected HashMap<V, HashSet<E>> graph;
	protected HashSet<E> edges;
	protected HashSet<E> edgeNullObject;
	protected GraphParameters graphParameters;
	
	protected AbstractAdjListGraph(GraphParameters params){
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
		if(checkVertices(edge.getVertices().get(0),edge.getVertices().get(1))) {
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
	public E addEdge(V vertex1, V vertex2) {
		E ret =null;
		if(checkVertices(vertex1,vertex2)){
			E temp = createEdge(vertex1,vertex2);
			if(addEdge(temp)){
				ret = temp;
			}
				
		}
		return ret;
	}	

	
	/** 
	 * Factory method must be overridden in any BaseGraph implementing class that inherits from AbstractAdjListGraph.java
	 * <p>
	 * Method defines and constructs the type of edge connecting the edges within the graph. i.e. a directed graph would override and return an Object of DirectedEdge type.  
	 * @param vertex1 first connecting vertex
	 * @param vertex2 second connecting vertex
	 * @return E extends Edge
	 */
	
	protected abstract E createEdge(V vertex1, V vertex2);

	
	// checks if an edge or it's reciprocal already exists, in the graph.
	protected boolean parallelOrReciprocalExists(E edge) {
		boolean ret = false;
		Set<E> edgeSet = graph.get(edge.getVertex(0));
		for(E e: edgeSet){
			if(e.isReciprocal(edge) || e.isParallel(edge) ){
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	protected boolean parallelExists(E edge) {
		boolean ret = false;
		if ( edges.contains(edge) ) {
			ret = true;
		}
		else {
			Set<E> edgeSet = graph.get(edge.getVertex(0));
			for(E e : edgeSet) {
				if(e.isParallel(e)) {
					ret = true;
					break;
				}
			}
		}	
		return ret;
	}
	
	protected boolean reciprocalExists(E edge) {
		boolean ret = false;
		Set<E> edgeSet = graph.get(edge.getVertex(0));
		for(E e : edgeSet) {
			if(e.isReciprocal(e)) {
				ret = true;
				break;
			}
		}
		return ret;
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

