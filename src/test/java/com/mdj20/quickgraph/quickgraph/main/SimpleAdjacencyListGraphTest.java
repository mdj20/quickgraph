package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
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
		int nVert =10 ;
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
		int nVert = 10;
		int nRemovedEdges = 3;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		ArrayList<Edge<Integer>> removed = new ArrayList<Edge<Integer>>();
		ArrayList<Edge<Integer>> edgeList = new ArrayList<Edge<Integer>>(graph.getEdges());
		int nEdgeBefore = edgeList.size();
		for(int i = 0 ; i < nRemovedEdges ; i++){
			removed.add(edgeList.get(i));
			graph.removeEdge(edgeList.get(i));
		}
		
		for(Edge<Integer> edge: removed){
			assertTrue(!graph.getEdges().contains(edge));  // check if edge is removed from graph edgeList

			assertTrue(!graph.getConnectingEdges(edge.getVertex(0)).contains(edge)); // check if edge is removed from outgoing edge via edge.getConnectingEdges() 
			assertTrue(!graph.getConnectingEdges(edge.getVertex(1)).contains(edge));
		}
		assertTrue(graph.getEdges().size()==nEdgeBefore-removed.size());
	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		Set<Integer> verts = graph.getVertices();
		assertTrue(verts.size()==nVert);
		for(int i = 0 ; i< nVert ; i++) {
			assertTrue(verts.contains(i));
		}
	}

	@Test
	public void testGetEdges() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		Set<Edge<Integer>> edeeList = graph.getEdges();
		
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
		HashMap<Integer,ArrayList<Integer>> endpointMap =  completeGraphEndpointMap(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
			assertTrue(endpointMap.get(i).size()==adjacent.size());
			
			for(Edge<Integer> edge: adjacent){
				assertTrue(endpointMap.get(i).contains(edge.getOpposingVertex(i)));
				
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
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		Set<Edge<Integer>> edgeList = graph.getEdges();
		for(int i = 0 ; i < nVert ; i++){
			Set<Edge<Integer>> outgoing = graph.getOutgoingEdges(i);
			Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
			assertTrue(outgoing.size()==adjacent.size());
			for(Edge<Integer> edge: outgoing){
				assertTrue(adjacent.contains(edge));
				assertTrue(edgeList.contains(edge));
			}
		}
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 10;
		SimpleAdjacencyListGraph<Integer> graph = createCompleteGraph(nVert);
		Set<Edge<Integer>> edgeList = graph.getEdges();
		for(int i = 0 ; i < nVert ; i++){
			Set<Edge<Integer>> incoming = graph.getIncomingEdges(i);
			Set<Edge<Integer>> adjacent = graph.getConnectingEdges(i);
			assertTrue(incoming.size()==adjacent.size());
			for(Edge<Integer> edge: incoming){
				assertTrue(adjacent.contains(edge));
				assertTrue(edgeList.contains(edge));
			}
		}
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
	
	protected static HashMap<Integer,ArrayList<Integer>> completeGraphEndpointMap(int nVert){
		HashMap<Integer,ArrayList<Integer>> ret = new HashMap<Integer,ArrayList<Integer>>();
		for(int i = 0 ; i < nVert ; i++){
			ret.put(i,new ArrayList<Integer>());
		}
		for(Integer i : ret.keySet()){
			for(int j = 0 ; j < nVert ; j++){
				if(i!=j){
					ArrayList<Integer> temp = ret.get(i);
					temp.add(j);
				}
			}
		}
		return ret;
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
