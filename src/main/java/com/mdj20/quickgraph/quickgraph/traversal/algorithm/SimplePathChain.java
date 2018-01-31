package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import com.mdj20.quickgraph.quickgraph.main.Edge;

class SimplePathChain<V, E extends Edge<V>, I extends Comparable<I>> implements Comparable<SimplePathChain<V,E,I>> {
	
	V vertex; // target vertex
	E edge; // edge that connects vertex with current lowest weight / distance to source
	I val; // Intermediate value used 
	
	public V getVertex() {
		return vertex;
	}

	public E getEdge() {
		return edge;
	}
	
	public E setEdge(E edge) {
		E ret = this.edge;
		this.edge = edge;
		return ret;
	}

	public I getVal() {
		return val;
	}
	
	public I setValue(I value) {
		I temp = val;
		val=value;
		return temp;
	}

	SimplePathChain(V vertex, E edge, I intermediate){
		this.vertex = vertex;
		this.edge = edge;
		this.val = intermediate;
	}
	
	@Override
	public int compareTo(SimplePathChain<V,E,I> o) {
		return val.compareTo(o.val);
	}

}
