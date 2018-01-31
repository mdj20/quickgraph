package com.mdj20.quickgraph.quickgraph.main;
import java.util.Set;

/**
 * 
 * @author Matthew D. Jeffreys 
 *
 * @param <V> Vertex of graph
 * @param <E> Type of edge must extend Edge<V>
 */

public interface Graph<V,E extends Edge<V>> {
	/**
	 * Adds vertex V to set of vertices.
	 * 
	 *  @param vertex Object to be added
	 */
	public boolean addVertex(V vertex);
	
	/**	Adds edge object to graph, and updates internal structure to reflect it's inclusion as if it had be constructed via addEdge(V v1,V v2);
	 * 
	 * @param edge - edge 
	 * @return true if addition was successful.
	 */
	
	public boolean addEdge(E edge);
	
	/** Creates edge between vertex1 and vertex 2
	 *  
	 * @param vertex1 first vertex of the created edge.
	 * @param vertex2 second vertex of the created edge. 
	 */
	public boolean addEdge(V vertex1, V vertex2);
	
	/** 
	 * Removes vertex and and connecting edges associated with vertex, from the graph.
	 * <p>
	 * If vertex is found within graph it will remove the vertex from the graph. This will also remove any edge that connects to the vertex.
	 *  
	 * @param vertex vertex, to be removed.
	 */
	public void removeVertex(V vertex);
	/**
	 * Removes target edge from graph.
	 * 
	 * 
	 * @param edge edge object, to be removed
	 */
	public void removeEdge(E edge);
	/**
	 * Returns set of graph vertices.
	 * <p>
	 * Returns set of all vertices that have been added to the graph. 
	 * Will return an empty set if there are currently no vertices. 
	 * 
	 * @return Set<V> set of graph vertices.
	 */
	public Set<V> getVertices();
	/**
	 * Returns set of all edges.
	 * @return set of edges.
	 */
	public Set<E> getEdges();
	/**
	 * Returns set containing vertices that are adjacent to argument vertex.
	 * 
	 * @param targetVertex target vertex 
	 * @return Set of vertices adjacent to 
	 */
	public Set<V> getAdjacentVertices(V targetVertex);
	/**
	 * Returns set of all edges connected to targeTvertex, both incoming and outgoing 
	 * 
	 * @param targetVertex 
	 * @return Set of edges that connect to targetVertex
	 */
	public Set<E> getConnectingEdges(V targetVertex);
	/**
	 * Returns a set of vertices that are the sink to and edge that targetVertex is the source. 
	 * <p>
	 * NOTE: in a non-directed graph this returns the same value as getAdjacentVertices()
	 * @param targetVertex the object/vertex 
	 * @return set of vertices that are sink vertices to outgoing edges.
	 */
	public Set<V> getOutgoingVertices(V targetVertex);
	/**
	 * Returns a set of vertices that are the source to an edge that targetVetex is the source.
	 * <p>
	 * 
	 * NOTE: in a non-directed graph this returns the same value as getAdjacentVertices()
	 * @param targetVertex the object/vertex
	 * @return set of vertices that are sink vertices to incoming edges.
	 */
	public Set<V> getIncomingVertices(V targetVertex);
	/**
	 * Returns a set of all edges that targetVertex is the source of.
	 * <p>
	 * NOTE: in a non-directed graph this returns the same value as getAdjacentEdges()
	 * @param targetVertex the object/vertex 
	 * @return set of outgoing edges
	 */
	public Set<E> getOutgoingEdges(V targetVertex);
	
	/**
	 * 
	 * Returns a set of all edges that targetVertex is the source of.
	 * <p>
	 * NOTE: in a non-directed graph this returns the same value as getAdjacentEdges()
	 * @param targetVertex the object/vertex
	 * @return set of incoming edges
	 */
	public Set<E> getIncomingEdges(V targetVertex);
}
