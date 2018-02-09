package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int removed = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		subGraph.removeVertex(removed);
		Set<Integer> vertSet = subGraph.getVertices();
		Set<Integer> superVertSet = graph.getVertices();
		assertTrue(!  (vertSet.contains(removed) || superVertSet.contains(removed)) );
		Set<Edge<Integer>> superEdgeSet = graph.getEdges();
		for (Edge<Integer> e : superEdgeSet) {
			assertTrue(!e.getVertices().contains(removed));
		}
		
	}

	@Test
	public void testRemoveEdge() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		ArrayList<Edge<Integer>> edgeList = new ArrayList<Edge<Integer>>(subGraph.getEdges());
		Edge<Integer> removed = edgeList.get(0);
		subGraph.removeEdge(removed);
		edgeList = new ArrayList<Edge<Integer>>(subGraph.getEdges());
		ArrayList<Edge<Integer>> superEdgeList = new ArrayList<Edge<Integer>>(graph.getEdges());
		assertTrue(!edgeList.contains(removed));
		assertTrue(!superEdgeList.contains(removed));
	}

	@Test
	public void testGetVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		Set<Integer> vertSet = subGraph.getVertices();
		for(Integer i : vertSet) {
			assertTrue(subVertex.contains(i));
		}

	}

	@Test
	public void testGetEdges() {
		
	}

	@Test
	public void testGetAdjacentVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Integer> vertSet = subGraph.getAdjacentVertices(tested);
		for(Integer i : vertSet) {
			actual[i] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetConnectingEdges() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Edge<Integer>> edgeSet = subGraph.getConnectingEdges(tested);
		for(Edge<Integer> e : edgeSet) {
			actual[e.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetOutgoingVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Integer> vertSet = subGraph.getOutgoingVertices(tested);
		for(Integer i : vertSet) {
			actual[i] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetIncomingVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Integer> vertSet = subGraph.getIncomingVertices(tested);
		for(Integer i : vertSet) {
			actual[i] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetOutgoingEdges() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Edge<Integer>> edgeSet = subGraph.getOutgoingEdges(tested);
		for(Edge<Integer> e : edgeSet) {
			actual[e.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetIncomingEdges() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		UserGraph<Integer> graph = FastGraphBuilder.completeSimpleIntegerGraph(nVert);
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subVertex);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		expected[tested] = false;
		Arrays.fill(actual, false);
		Set<Edge<Integer>> edgeSet = subGraph.getIncomingEdges(tested);
		for(Edge<Integer> e : edgeSet) {
			actual[e.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(expected,actual);
	}

}
