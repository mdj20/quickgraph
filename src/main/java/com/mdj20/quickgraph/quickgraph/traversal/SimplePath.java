package com.mdj20.quickgraph.quickgraph.traversal;

import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;


/**
 * @author Matthew D. Jeffreys
 */



public class SimplePath<V,E extends Edge<V>> extends AbstractPath<V,E> implements Path<V,E> {
	public SimplePath(V source, V sink, List<E> edgeList) {
		super(source, sink, edgeList);
	}
}
