package com.mdj20.quickgraph.quickgraph.main;

/** Connects 2 objects (V) designating one as the source and one as the sink. 
 *  
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type
 */


public interface Directed<V> {
	/**
	 * returns object V designated as source.
	 * @return source
	 */
	public V getSource();
	/**
	 * returns object designated as sink
	 * @return sink
	 */
	public V getSink();
}
