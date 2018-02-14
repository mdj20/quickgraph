package com.mdj20.quickgraph.quickgraph.main;

/**
 * Interface implemented defines an object with a weight.
 *  
 * @author Matthew D. Jeffreys
 *
 * @param <W> type of Weight
 * 
 * 
 * 
 */

public interface Weighted<W> {
	/**
	 * returns W value of edge.
	 * @return W value of edge
	 */
	public W getWeight();
	
	/**
	 * Sets weight of edge. 
	 * @param weight value of edge to be set.
	 */
	public void setWeight(W weight);
}
