package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleWeightedDirectionalEdgeTest extends SimpleDirectionalEdgeTest {

	@Test
	public void testGetWeight() {
		int x = 0 , y = 1 , w =5;
		SimpleWeightedEdge<Integer,Integer> edge = new SimpleWeightedEdge<Integer,Integer>(x,y,w);
		assertTrue(edge.getWeight().equals(w));
		assertTrue(!edge.getWeight().equals(x));
		assertTrue(!edge.getWeight().equals(y));
	}

	@Test
	public void testSetWeight() {
		int x = 0 , y = 1 , w0 =5, w1 =10;
		SimpleWeightedEdge<Integer,Integer> edge = new SimpleWeightedEdge<Integer,Integer>(x,y,w0);
		edge.setWeight(w1);
		assertTrue(edge.getWeight().equals(w1));
		assertTrue(!edge.getWeight().equals(w0));
		assertTrue(!edge.getWeight().equals(x));
		assertTrue(!edge.getWeight().equals(y));
	}

}
