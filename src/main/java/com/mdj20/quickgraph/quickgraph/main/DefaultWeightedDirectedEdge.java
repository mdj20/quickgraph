package com.mdj20.quickgraph.quickgraph.main;

/** Concrete implementation of WeightedDirectedEdge.
 * 
 * @author Matthew D. Jeffreys
 * 
 * @param <V> Vertex Type
 * @param <W> Weight Type
 */

public class DefaultWeightedDirectedEdge<V, W> extends DefaultDirectedEdge<V> implements WeightedDirectedEdge<V,W> {

	private W weight;
	
	DefaultWeightedDirectedEdge(V source, V sink){
		this(source, sink, null);
	}
	
	DefaultWeightedDirectedEdge(V source, V sink, W weight) {
		super(source, sink);
		this.weight = weight;
	}

	@Override
	public W getWeight() {
		return weight;
	}

	@Override
	public void setWeight(W weight) {
		this.weight = weight;
	}

}
