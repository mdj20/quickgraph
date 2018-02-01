package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SimpleEdgeTest {

	@Test
	public void testGetVertices() {
		int  x = 0 , y = 1;
		SimpleEdge<Integer> edge = new SimpleEdge<Integer>(x,y);
		List<Integer> verts = edge.getVertices();
		assertTrue(verts.get(0).equals(x));
		assertTrue(verts.get(1).equals(y));
	}

	@Test
	public void testGetVertex() {
		int  x = 0 , y = 1;
		SimpleEdge<Integer> edge = new SimpleEdge<Integer>(x,y);
		assertTrue(edge.getVertex(0).equals(x));
		assertTrue(edge.getVertex(1).equals(y));
	}

	@Test
	public void testGetOpposingVertex() {
		int  x = 0 , y = 1;
		SimpleEdge<Integer> edge = new SimpleEdge<Integer>(x,y);
		assertTrue(edge.getOpposingVertex(x).equals(y));
		assertTrue(edge.getOpposingVertex(y).equals(x));
	}

	@Test
	public void testIsReciprical() {
		int  x = 0 , y = 1;
		SimpleEdge<Integer> edge = new SimpleEdge<Integer>(x,y);
		SimpleEdge<Integer> rec = new SimpleEdge<Integer>(y,x);
		assertTrue(edge.isReciprical(rec));
		assertTrue(!edge.isReciprical(edge));
	}

	@Test
	public void testIsParallel() {
		int  x = 0 , y = 1;
		SimpleEdge<Integer> edge = new SimpleEdge<Integer>(x,y);
		SimpleEdge<Integer> par = new SimpleEdge<Integer>(x,y);
		SimpleEdge<Integer> rec = new SimpleEdge<Integer>(y,x);
		assertTrue(edge.isParallel(par));
		assertTrue(edge.isParallel(edge));
		assertTrue(!edge.isParallel(rec));
	}

	
}
