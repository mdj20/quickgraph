package com.mdj20.quickgraph.quickgraph.main;

/**Iterface describes a weighted graph.
 * 
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type. 
 * @param <E> Edge Type extends WeightedEdge<V,W>.
 * @param <W> Weight type.
 */


public interface BaseWeightedGraph<V, E extends WeightedEdge<V,W>, W> extends BaseGraph<V,E> {

	/**
	 * Creates and adds edge with the weight specified by weight.
	 *  <p>
	 *  Note: the non weighted addEdge(V,V) is unsupported in weighted graph hierarchy. 
	 * @param vertex1 First vertex connected by edge.
	 * @param vertex2 Second vertex connected by edge
	 * @param weight Weight of the edge.
	 */
	
	public E addEdge(V vertex1, V vertex2, W weight);
	
	
	/**
	 * Sets default weight for edges created with addEdge(V vertex1, V vertex2)
	 * 
	 * @param weight value to be set as default weight.
	 * @return previous default weight, null if no previous weight was set.
	 */
	public W setDefaultWeight(W weight);
	
	/**
	 * Gets the default weight value, that is used when edges are created without an weight value specified. 
	 * 
	 * @return default weight value or null if the value has not been set.
	 */
	
	public W getDefaultWeight();
}
