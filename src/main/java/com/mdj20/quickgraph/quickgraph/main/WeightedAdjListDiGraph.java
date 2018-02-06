package com.mdj20.quickgraph.quickgraph.main;

/** Concrete implementation of a directed and weighted graph
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type.
 * @param <W> Weight Type.
 */

public class WeightedAdjListDiGraph<V, W> extends AbstractAdjListDiGraph<V,WeightedDirectedEdge<V,W>> 
	implements UserWeightedDiGraph<V,W>, DiGraph<V,WeightedDirectedEdge<V,W>>, WeightedGraph<V,WeightedDirectedEdge<V,W>,W> {

	@Override
	public WeightedDirectedEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new DefaultWeightedDirectedEdge<V,W>(vertex1,vertex2);
	}

	@Override
	public boolean addEdge(V vertex1, V vertex2, W weight) {
		boolean ret = false;
		if(checkVertices(vertex1,vertex2)){
			WeightedDirectedEdge<V,W> edge = new DefaultWeightedDirectedEdge<V,W>(vertex1,vertex2,weight); 
			addEdgeToGraph(vertex1,edge);
			edges.add(edge);
			ret =true;
		}
		return ret;
	}

}
