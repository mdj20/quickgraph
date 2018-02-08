package com.mdj20.quickgraph.quickgraph.main.sub;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.Edge;


/**
 * Sub Graph decorator. 
 * 
 * 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> type of BaseGraph
 * @param <V> type of Vertex
 * @param <E> type of Edge
 */

abstract class AbstractSubGraph<G extends BaseGraph<V,E>,V,E extends Edge<V>> implements BaseGraph<V,E>{

	protected G parentGraph;
	protected Set<V> subVertex;
	protected Set<V> unmodifiableVertex;
	//protected Set<E> subEdge;  //remove subEdge
	//protected Set<E> unmodifiableEdge;
	
	
	protected AbstractSubGraph(G baseGraph, Set<V> vertices, Set<E> edges){
		throw new UnsupportedOperationException("Construtor not supported...");
	}
	protected AbstractSubGraph(G baseGraph, Set<V> vertices) {
		if(baseGraph == null || vertices == null) {
			throw new NullPointerException(" Neither baseGraph or vertices can be null. ");
		}
		parentGraph = baseGraph;
		subVertex = checkVertexList(baseGraph.getVertices(),vertices);
		//subEdge = buildEdgeList(subVertex,baseGraph.getEdges());
		unmodifiableVertex = Collections.unmodifiableSet(subVertex);
		//unmodifiableEdge = Collections.unmodifiableSet(subEdge);
	}
	
	

	// adds vertex to subgraph and adds vertex to parent graph if it isn't already a member.
	@Override
	public boolean addVertex(V vertex) {
		boolean ret = false;
		if(!parentGraph.getVertices().contains(vertex)) {
			ret = parentGraph.addVertex(vertex);
			if (ret) {
				subVertex.add(vertex);
			}
		}
		else if (!subVertex.contains(vertex)) {
			subVertex.add(vertex);
			ret = true;
		}
		return false;
	}

	@Override
	public boolean addEdge(E edge) {
		boolean ret = false;
		if(checkVertices(edge.getVertex(0),edge.getVertex(1))) {
			ret = parentGraph.addEdge(edge);
		}
		return ret;
	}

	@Override
	public E addEdge(V vertex1, V vertex2) {
		E ret = null;
		if(checkVertices(vertex1,vertex2)) {
			ret = parentGraph.addEdge(vertex1, vertex2);
		}
		return ret;
	}

	@Override
	public void removeVertex(V vertex) {
		if(checkSubVert(vertex)){
			subVertex.remove(vertex);
			parentGraph.removeVertex(vertex);
		}
		
	}

	@Override
	public void removeEdge(E edge) {
		if ( checkVertices(edge.getVertex(0),edge.getVertex(1)) ){
			parentGraph.removeEdge(edge);
		}
	}

	@Override
	public Set<V> getVertices() {
		return unmodifiableVertex;
	}

	@Override
	public Set<E> getEdges() {
		Set<E> ret = new HashSet<E>();
		ret = buildEdgeList(subVertex,parentGraph.getEdges());
		return ret;
	}

	@Override
	public Set<V> getAdjacentVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getAdjacentVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getConnectingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = buildEdgeList(subVertex,parentGraph.getConnectingEdges(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<V> getOutgoingVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getOutgoingVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<V> getIncomingVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getIncomingVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getOutgoingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = buildEdgeList(subVertex,parentGraph.getOutgoingEdges(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getIncomingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = buildEdgeList(subVertex,parentGraph.getIncomingEdges(targetVertex));
		}
		return ret;
	}
	
	protected boolean checkSubVert(V v) {
		return subVertex.contains(v);
	}
	protected boolean checkSubVert(V v1, V v2) {
		return checkSubVert(v1) && checkSubVert(v2);
	}
	
	
	// takes a set of vertices and removes any that aren't in subVetex.
	protected Set<V> trimVert(Set<V> verts){
		verts.retainAll(subVertex);
		return  verts;
	}
		

	
	// REMOVED HELPER METHODS BELOW, 
	
	/*
	// takes a set of edges and removes any that aren't in subEdges.
	protected Set<E> trimEdge(Set<E> edges)	{
		Set<E> ret = new HashSet<E>();
		for(E e: edges) {
			if (subEdge.contains(e))
				ret.add(e);
		}
		return ret;
	}
	
	
	// removes all edges from subEdge that connect to vertex v.
	protected void removeAssociatedSubEdge(V v) {
		Set<E> removed = new HashSet<E>();
		for(E e : subEdge) {
			if(e.getVertices().contains(v)) {
				removed.add(e);
			}
		}
		subEdge.removeAll(removed);
	}
	
	
	// removes all edges from subEdge that connect to vertex v1 or v2.
	protected void removedAssociatedSubEdge(V v1, V v2) {
		Set<E> removed = new HashSet<E>();
		for(E e : subEdge) {
			if(e.getVertices().contains(v1) || e.getVertices().contains(v2)) {
				removed.add(e);
			}
		}
		subEdge.removeAll(removed);		
	}
	*/
	
	// creates aggregate set of edges in that are connected on both ends to vertices that exits in baseVertex set. 
	protected Set<E> buildEdgeList(Set<V> vertices, Set<E> edges){
		HashSet<E> aggregateEdges = new HashSet<E>();
		for(E e: edges) {
			if( vertices.contains(e.getVertex(0)) && vertices.contains(e.getVertex(1)) ) {
				aggregateEdges.add(e);
			}
		}
		return aggregateEdges;	
	}
	
	//returns set<V> of all tested that are contained in valid.
	protected Set<V> checkVertexList(Set<V> valid, Set<V> tested){
		tested.retainAll(valid);
		HashSet<V> checked = new HashSet<V>(tested);
		return checked;
	}
	protected boolean checkVertices(V v1 , V v2) {
		return subVertex.contains(v1) && subVertex.contains(v2);
	}
	
}
