package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.SimpleAdjacencyListGraph;

public class SimpleAdjacencyListGraphTest {
	
	SimpleAdjacencyListGraph<Integer> testGraph;
	ArrayList<Integer> testValues;
	int nTestValues = 500;
	int nTestEdges = 100;
	@Before public void initialize() {
	      testGraph = new SimpleAdjacencyListGraph<Integer>();
	      testValues = new ArrayList<Integer> ();
	      for(int i = 0 ; i < nTestValues ; i++) {
	    	  testValues.add(i);
	      }
	}

	@Test
	public void testAddVertex() {
		SimpleAdjacencyListGraph<Integer> graph = new SimpleAdjacencyListGraph<Integer>();
		graph.addVertex(testValues.get(0));
		assertTrue(graph.getVertices().contains(0));
		assertTrue(graph.getVertices().size()==1);
	}

	@Test
	public void testAddEdgeE() {
		int nVert = 10;
		ArrayList<Edge<Integer>> edgeList = new ArrayList<Edge<Integer>>();
		SimpleAdjacencyListGraph<Integer> graph = new SimpleAdjacencyListGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			edgeList.add(graph.createEdge(i, (nVert-1)-i));
		}
		for(Edge<Integer> e: edgeList) {
			graph.addEdge(e);
		}
		Set<Edge<Integer>> edgeSet = graph.getEdges();
		for(Edge<Integer> e: edgeList) {
			assertTrue(edgeSet.contains(e));
		}
		
	}

	@Test
	public void testRemoveVertex() {
		int nVert = 10;
		int removedVertex = nVert-1;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		graph.removeVertex(removedVertex);
		assertTrue(!graph.getVertices().contains(nVert-1)); // test if vertex is removed...
		for(int i = 0 ; i <nVert-1 ; i++) {
			Set<Integer> neighbors = graph.getAdjacentVertices(i); // test if connecting edges are removed.
			assertTrue(!neighbors.contains(removedVertex));
		}
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
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Integer> adjacent = graph.getAdjacentVertices(i);
			for(int j = 0 ; j < nVert ; j++) {
				if(i==j) {
					assertTrue(!adjacent.contains(j));
				}
				else {
					assertTrue(adjacent.contains(j));
				}
				
			}
		}
	}

	@Test
	public void testGetConnectingEdges() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
			for(int j = 0 ; j < nVert ; j++) {
				if(i==j) {
					assertTrue(!adjacent.contains(j));
				}
				else {
					assertTrue(adjacent.contains(j));
				}
				
			}
		}
	}

	@Test
	public void testAddEdgeVV() {
		fail("Not yet implemented");
	}

	// Note: getOutgoingVertices()  returns the same values as  testGetAdjacentVertices in a non directed graph
	@Test
	public void testGetOutgoingVertices() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Integer> adjacent = graph.getOutgoingVertices(i);
			for(int j = 0 ; j < nVert ; j++) {
				if(i==j) {
					assertTrue(!adjacent.contains(j));
				}
				else {
					assertTrue(adjacent.contains(j));
				}
				
			}
		}
	}

	// Note: getIncomingVertices()  returns the same values as  testGetAdjacentVertices in a non directed graph.
	@Test
	public void testGetIncomingVertices() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Integer> adjacent = graph.getIncomingVertices(i);
			for(int j = 0 ; j < nVert ; j++) {
				if(i==j) {
					assertTrue(!adjacent.contains(j));
				}
				else {
					assertTrue(adjacent.contains(j));
				}
				
			}
		}
	}

	@Test
	public void testGetOutgoingEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncomingEdges() {
		fail("Not yet implemented");
	}
	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	protected static SimpleAdjacencyListGraph<Integer> createCompleteGraph(int nVertices){
		SimpleAdjacencyListGraph<Integer > graph = new SimpleAdjacencyListGraph<Integer >();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		for (int i = 0; i < nVertices ; i++) {
			for (int j = 0; j < nVertices ; j++) {
				if(i!=j) {
					graph.addEdge(i,j);
				}
			}
		}
		return graph;
	}
	
	// method provides a simple procedure for determining the vertices a edge will connect
	private int getEdgeVertex(ArrayList<Integer> testValues, int edge, int skip, boolean source){
		int index = testValues.size()%(edge*skip);
		int ret = 0;
		if(source){
			ret = testValues.get(index);
		}
		else{
			ret = testValues.get(testValues.size()-1-index);
		}
		return ret;
	}

}
