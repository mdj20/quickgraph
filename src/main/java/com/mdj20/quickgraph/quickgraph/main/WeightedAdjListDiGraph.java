package com.mdj20.quickgraph.quickgraph.main;

/** Concrete implementation of a directed and weighted graph
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type.
 * @param <W> Weight Type.
 */

public class WeightedAdjListDiGraph<V, W> extends AbstractWeightedAdjListGraph<V,WeightedDirectedEdge<V,W>,W> 
	implements UserWeightedDiGraph<V,W>, DiGraph<V,WeightedDirectedEdge<V,W>>, BaseWeightedGraph<V,WeightedDirectedEdge<V,W>,W> {

	public WeightedAdjListDiGraph() {
		super(GraphType.WEIGHTED_DIRECTED);
	}
	protected WeightedAdjListDiGraph(GraphParameters params) {
		super(GraphType.WEIGHTED_DIRECTED);
	}

	@Override
	public WeightedDirectedEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new DefaultWeightedDirectedEdge<V,W>(vertex1,vertex2,defaultWeight);
	}

	@Override
	public WeightedDirectedEdge<V,W> addEdge(V vertex1, V vertex2, W weight) {
		WeightedDirectedEdge<V,W> ret = null;
		if(checkVertices(vertex1,vertex2)){
			WeightedDirectedEdge<V,W> edge = new DefaultWeightedDirectedEdge<V,W>(vertex1,vertex2,weight); 
			addEdgeToGraph(vertex1,edge);
			edges.add(edge);
			ret = edge;
		}
		return ret;
	}

}
