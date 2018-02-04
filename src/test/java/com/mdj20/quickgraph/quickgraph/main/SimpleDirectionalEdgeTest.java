package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleDirectionalEdgeTest extends SimpleEdgeTest {

	@Test
	public void testGetSource() {
		int x = 0 , y = 1;
		DefaultDirectedEdge<Integer> edge = new DefaultDirectedEdge<Integer>(0,1);
		assertTrue(edge.getSource().equals(x));
		assertTrue(!edge.getSource().equals(y));
	}

	@Test
	public void testGetSink() {
		int x = 0 , y = 1;
		DefaultDirectedEdge<Integer> edge = new DefaultDirectedEdge<Integer>(0,1);
		assertTrue(edge.getSink().equals(y));
		assertTrue(!edge.getSink().equals(x));
	}

}
