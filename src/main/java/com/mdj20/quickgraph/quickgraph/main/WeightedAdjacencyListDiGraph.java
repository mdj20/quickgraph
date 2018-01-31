package com.mdj20.quickgraph.quickgraph.main;

public class WeightedAdjacencyListDiGraph<V, W> extends AbstractAdjacencyListDiGraph<V,WeightedDirectionalEdge<V,W>> 
	implements DiGraph<V,WeightedDirectionalEdge<V,W>>, WeightedGraph<V,WeightedDirectionalEdge<V,W>,W> {

	@Override
	public WeightedDirectionalEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new SimpleWeightedDirectionalEdge<V,W>(vertex1,vertex2);
	}

	@Override
	public boolean addEdge(V vertex1, V vertex2, W weight) {
		boolean ret = false;
		if(checkVertices(vertex1,vertex2)){
			WeightedDirectionalEdge<V,W> edge = new SimpleWeightedDirectionalEdge<V,W>(vertex1,vertex2,weight); 
			addEdgeToGraph(vertex1,edge);
			edges.add(edge);
			ret =true;
		}
		return true;
	}

}
