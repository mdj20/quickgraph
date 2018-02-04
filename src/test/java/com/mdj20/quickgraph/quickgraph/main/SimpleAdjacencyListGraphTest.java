package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.AdjListGraph;

public class SimpleAdjacencyListGraphTest {
	
	AdjListGraph<Integer> testGraph;
	ArrayList<Integer> testValues;
	int nTestValues = 500;
	int nTestEdges = 100;
	@Before public void initialize() {
	      testGraph = new AdjListGraph<Integer>();
	      testValues = new ArrayList<Integer> ();
	      for(int i = 0 ; i < nTestValues ; i++) {
	    	  testValues.add(i);
	      }
	}

	@Test
	public void testAddVertex() {
		AdjListGraph<Integer> graph = new AdjListGraph<Integer>();
		graph.addVertex(testValues.get(0));
		assertTrue(graph.getVertices().contains(0));
		assertTrue(graph.getVertices().size()==1);
	}

	@Test
	public void testAddEdgeE() {
		int nVert = 10;
		ArrayList<Edge<Integer>> edgeList = new ArrayList<Edge<Integer>>();
		AdjListGraph<Integer> graph = new AdjListGraph<Integer>();
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
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		graph.removeVertex(removedVertex);
		assertTrue(!graph.getVertices().contains(nVert-1)); // test if vertex is removed...
		for(int i = 0 ; i <nVert-1 ; i++) {
			Set<Integer> neighbors = graph.getAdjacentVertices(i); // test if connecting edges are removed.
			assertTrue(!neighbors.contains(removedVertex));
		}
	}

	@Test
	public void testRemoveEdge() {
		int nVert = 10;
		int removedEdgeVertex = nVert-1;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		Set<Edge<Integer>> removedEdges = graph.getConnectingEdges(removedEdgeVertex);
		
		for(Edge<Integer> e: new ArrayList<Edge<Integer>>(removedEdges)){
			graph.removeEdge(e);
		}
		for(int i = 0 ; i < nVert ; i++) {
			if(i!=removedEdgeVertex){
				Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
				boolean[] actual = new boolean[nVert];
				boolean[] expected = new boolean[nVert];
				Arrays.fill(actual, false);
				Arrays.fill(expected, true);
				expected[i] = false;
				expected[removedEdgeVertex] = false;
				for(Edge<Integer> edge: adjacent){
					int opposite = edge.getOpposingVertex(i);
					actual[opposite] = true;
				}
				assertArrayEquals(expected,actual);	
			}
		}

	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		Set<Integer> verts = graph.getVertices();
		assertTrue(verts.size()==nVert);
		for(int i = 0 ; i< nVert ; i++) {
			assertTrue(verts.contains(i));
		}
	}

	@Test
	public void testGetEdges() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		Set<Edge<Integer>> edeeList = graph.getEdges();
		
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
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
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
			boolean[] actual = new boolean[nVert];
			boolean[] expected = new boolean[nVert];
			Arrays.fill(actual, false);
			Arrays.fill(expected, true);
			expected[i] = false;
			int nConnectingEdge = 0;
			for(Edge<Integer> edge: adjacent){
				int opposite = edge.getOpposingVertex(i);
				actual[opposite] = true;
				nConnectingEdge++;
			}
			
			assertArrayEquals(expected,actual);
			assertTrue(nConnectingEdge==nVert-1);
		}
	}

	@Test
	public void testAddEdgeVV() {
		int nVert = 5;
		int nEdge = nVert;
		AdjListGraph<Integer> graph = new AdjListGraph<Integer>();
		ArrayList<Integer> sources = new ArrayList<Integer>();
		ArrayList<Integer> sinks = new ArrayList<Integer>();
		for(int i = 0 ; i < nVert ; i++){
			graph.addVertex(i);
		}
		int j = nVert/2;
		for(int i = 0 ; i < nVert;i++){
			j = (j==nVert) ? j%nVert : j ;
			sources.add(i);
			sinks.add(j);
			graph.addEdge(i,j);
		}
		ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>(graph.getEdges());
		boolean[] actual = new boolean[nEdge];
		boolean[] expected = new boolean[nEdge];
		Arrays.fill(actual, false);
		Arrays.fill(expected, true);
		for(int i = 0 ; i < edges.size() ; i++){
			int source = edges.get(i).getVertex(0);
			int sink = edges.get(i).getOpposingVertex(source);
			for( j = 0 ; j < sources.size() ; j++ ){
				if(sources.get(j).equals(source) && sinks.get(j).equals(sink)){
					actual[i] = true;
				}
			}
		}
		assertTrue(nEdge==edges.size());
		assertArrayEquals(actual,expected);	
	}

	// Note: getOutgoingVertices()  returns the same values as  testGetAdjacentVertices in a non directed graph
	@Test
	public void testGetOutgoingVertices() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
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
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
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
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Edge<Integer>> adjacent = graph.getOutgoingEdges(i);
			boolean[] actual = new boolean[nVert];
			boolean[] expected = new boolean[nVert];
			Arrays.fill(actual, false);
			Arrays.fill(expected, true);
			expected[i] = false;
			int nConnectingEdge = 0;
			for(Edge<Integer> edge: adjacent){
				int opposite = edge.getOpposingVertex(i);
				actual[opposite] = true;
				nConnectingEdge++;
			}
			
			assertArrayEquals(expected,actual);

			System.out.println(adjacent.size()+" "+(nVert-1));
			assertTrue(nConnectingEdge==nVert-1);
		}
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Edge<Integer>> adjacent = graph.getIncomingEdges(i);
			boolean[] actual = new boolean[nVert];
			boolean[] expected = new boolean[nVert];
			Arrays.fill(actual, false);
			Arrays.fill(expected, true);
			expected[i] = false;
			int nConnectingEdge = 0;
			for(Edge<Integer> edge: adjacent){
				int opposite = edge.getOpposingVertex(i);
				actual[opposite] = true;
				nConnectingEdge++;
			}
			
			assertArrayEquals(expected,actual);

			System.out.println(adjacent.size()+" "+(nVert-1));
			assertTrue(nConnectingEdge==nVert-1);
		}
	}
	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	protected static AdjListGraph<Integer> completeSimpleIntegerGraph(int nVertices){
		AdjListGraph<Integer > graph = new AdjListGraph<Integer >();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		for (int i = 0; i < nVertices ; i++) {
			for (int j = i; j < nVertices ; j++) {
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
