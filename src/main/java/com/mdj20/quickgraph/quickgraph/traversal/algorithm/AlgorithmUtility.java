package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.BaseWeightedGraph;


/** Contains a series of static utility methods used in graph traversal algorithms.
 * 
 * @author Matthew D. Jeffreys
 *
 */

public class AlgorithmUtility {
	
	/** Determines whether any of a weighted graph's edges have a negative. 
	 * 
	 * @param graph  
	 * @return true if the graph has a negative edge
	 */
	
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number> boolean hasNegativeEdge(BaseWeightedGraph<V,E,W> graph){
		boolean ret = false;
		Double zero = Double.valueOf(0);
		for(WeightedEdge<V,W> we: graph.getEdges()) {
			if(zero.compareTo(we.getWeight().doubleValue())>0) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
