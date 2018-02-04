package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;

/**
 * Represents a walk through a graph. Contains a list of edges 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type 
 * @param <E> Edge Type (E extends edge)
 */


public abstract class AbstractPath<V, E extends Edge<V>> implements Path<V,E>{
	
	protected List<E> edgeList;
	protected List<V> vertexList;
	protected V source;
	protected V sink;
	
	protected AbstractPath(V source, V sink, List<E> edgeList ) {
		this.source = source;
		this.sink = sink;
		this.edgeList = Collections.unmodifiableList(edgeList);
		vertexList =  Collections.unmodifiableList(inferVertexList());
	}

	@Override
	public List<E> getEdgeList() {
		return edgeList;
	}

	@Override
	public V getSource() {
		return source;
	}

	@Override
	public V getSink() {
		return sink;
	}

	@Override
	public V getVertexAt(int i) {
		return vertexList.get(i);
	}
	
	@Override 
	public int getNumVertices(){
		return vertexList.size();
	}

	protected List<V> inferVertexList(){
		ArrayList<V> list = new ArrayList<V>();
		V temp = source;
		list.add(source);
		for(E e:edgeList){
			list.add(e.getOpposingVertex(temp));
			temp = e.getOpposingVertex(temp);
		}
		if(!list.get(0).equals(source)  || list.get(list.size()-1).equals(sink)){
			throw new IllegalStateException("Unable to infer vertex list");
		}
		return list;
	}
	
	// checks if edgeList is a valid path chaining vertices in the proper order.
	protected boolean checkEdgeListValidity(){
		boolean ret = true;
		V temp = source, temp2 = null;
		for(E e: edgeList){
			temp2 = e.getOpposingVertex(temp);
			if(temp2==null){
				ret = false;
				break;
			}
			else{
				temp = temp2;
			}
		}
		return ret;
	}
	
}
