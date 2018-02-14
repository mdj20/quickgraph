package com.mdj20.quickgraph.quickgraph.main;
import java.util.HashSet;
import java.util.Set;

/** Concrete implementation of BaseDiGraph
 *  
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type.
 */

public class AdjListDiGraph<V> extends AbstractAdjListDiGraph<V,DirectedEdge<V>> implements DiGraph<V>, BaseDiGraph<V,DirectedEdge<V>> {

	public AdjListDiGraph(){
		super(GraphType.DIRECTED);
	}
	
	/**
	 *  Method returns a DirectedEdge connecting vertex1 to vertex2 as the respective source and sink of the edge.
	 */
	
	@Override
	public DirectedEdge<V> createEdge(V vertex1, V vertex2) {
		return new DefaultDirectedEdge<V>(vertex1,vertex2);
	}
}
