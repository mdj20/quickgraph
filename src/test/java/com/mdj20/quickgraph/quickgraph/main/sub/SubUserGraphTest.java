package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.AdjListGraph;
import com.mdj20.quickgraph.quickgraph.main.DefaultEdge;
import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.UserGraph;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubUserGraphTest {

	@Test
	public void testAddVertex() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = subVertexLimit+1;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		
		// addVertex that exists in super Graph
		subGraph.addVertex(added);
		
		// test if vertex exists
		assertTrue(subGraph.getVertices().contains(added));
		
		//test if updated edge size is correct
		Set<Edge<Integer>> superEdge = graph.getConnectingEdges(added);
		Set<Edge<Integer>> edge = subGraph.getConnectingEdges(added);
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
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		Edge<Integer> edge = new DefaultEdge<Integer>(0,subVertexLimit-1);
		subGraph.addEdge(edge);
		Set<Edge<Integer>> edgeSet = subGraph.getEdges();
		Set<Edge<Integer>> superEdgeSet = graph.getEdges();
		assertTrue(edgeSet.contains(edge));
		assertTrue(superEdgeSet.contains(edge));
	}

	@Test
	public void testAddEdgeVV() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		Edge<Integer> edge = subGraph.addEdge(0,subVertexLimit-1);
		Set<Edge<Integer>> edgeSet = subGraph.getEdges();
		Set<Edge<Integer>> superEdgeSet = graph.getEdges();
		assertTrue(edgeSet.contains(edge));
		assertTrue(superEdgeSet.contains(edge));

		
	}

	@Test
	public void testRemoveVertex() {
		
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
