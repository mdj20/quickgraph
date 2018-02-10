package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubWeightedGraphTest {

	@Test
	public void testAddEdgeVVW() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = subVertexLimit+1;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		
		// Add vertex
		subGraph.addVertex(added);
		
		// assert it was added
		assertTrue(subGraph.getVertices().contains(added));
		
		Set<WeightedEdge<Integer, Integer>> superEdge = graph.getConnectingEdges(added);
		Set<WeightedEdge<Integer,Integer>> edge = subGraph.getConnectingEdges(added);
		assertTrue(edge.size()==subVertexLimit);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		// check if all edges are correct
		for(Edge<Integer> e: edge) {
			if(subVertex.contains(e.getOpposingVertex(added))) {
				actual[e.getOpposingVertex(added)] = true;
			}
		}
		assertArrayEquals(actual,expected); // test 
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
