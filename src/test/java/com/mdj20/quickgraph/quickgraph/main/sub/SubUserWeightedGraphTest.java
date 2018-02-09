package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListGraph;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubUserWeightedGraphTest {

	@Test
	public void testAddEdgeVVW() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubUserWeightedGraph<Integer,Integer> subGraph = new SubUserWeightedGraph<Integer,Integer>(graph,subVertex);
	}
 
	@Test
	public void testAddEdgeE() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConnectingEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOutgoingVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncomingVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOutgoingEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncomingEdges() {
		fail("Not yet implemented");
	}

}
