package com.mdj20.quickgraph.quickgraph.main;
import java.util.HashSet;
import java.util.Set;

/** Concrete implementation of DiGraph
 *  
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type.
 */

public class AdjListDiGraph<V> extends AbstractAdjListDiGraph<V,DirectedEdge<V>> implements DiGraph<V,DirectedEdge<V>> {

	public AdjListDiGraph(){
		super(GraphType.DIRECTED);
	}
	
	@Override
	public DirectedEdge<V> createEdge(V vertex1, V vertex2) {
		return new DefaultDirectedEdge<V>(vertex1,vertex2);
	}
}
