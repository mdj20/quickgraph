package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SimpleAdjacencyListDiGraphTest {

	@Test
	public void testCreateEdgeVV() {
		int nVert = 10;
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			graph.addVertex(i);
		}
		for(int i = 0 ; i < (nVert/2); i++) {
			graph.addEdge(i, (nVert-1)-i);
		}
		Set<DirectionalEdge<Integer>> edgeSet = graph.getEdges();
		assertTrue(edgeSet.size()==nVert/2); // check number of edges
		for(int i = 0 ; i < nVert/2 ; i++) {
			if(i<nVert/2) {
				Set<DirectionalEdge<Integer>> vertEdge = graph.getConnectingEdges(i);
				assertTrue(vertEdge.size()==1);  // check each vert has the right amount of edges.
				DirectionalEdge<Integer> edge = vertEdge.iterator().next();
				assertTrue((edge.getSource()==i)&&edge.getSink()==((nVert-1)-i));  // check that the edges are correct.
			}
		}
	}

	@Test
	public void testAddEdgeE() {
		int nVert = 10;
		ArrayList<DirectionalEdge<Integer>> edgeList = new ArrayList<DirectionalEdge<Integer>>();
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			edgeList.add(graph.createEdge(i, (nVert-1)-i));
		}
		for(DirectionalEdge<Integer> e: edgeList) {
			graph.addEdge(e);
		}
		Set<DirectionalEdge<Integer>> edgeSet = graph.getEdges();
		for(DirectionalEdge<Integer> e: edgeList) {
			assertTrue(edgeSet.contains(e));
		}
	}

	@Test
	public void testRemoveVertex() {
		int nVert = 10;
		int removedVertex = nVert-1;
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
		graph.removeVertex(removedVertex);
		assertTrue(!graph.getVertices().contains(nVert-1)); // test if vertex is removed...
		for(int i = 0 ; i <nVert-1 ; i++) {
			Set<Integer> neighbors = graph.getAdjacentVertices(i);
			assertTrue(!neighbors.contains(removedVertex));
		}
	}

	@Test
	public void testRemoveEdge() {
		int nVert = 10;
		int removedEdge = 3;
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
		
	}

	@Test
	public void testGetConnectingEdges() {
		int nVert = 10;
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < 10 ; i++) {
			Set<DirectionalEdge<Integer>> adjacent = graph.getConnectingEdges(i);
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
	public void testGetOutgoingVertices() {
		int nVert = 10;

		ArrayList<Integer> source = new ArrayList<Integer>();
		ArrayList<Integer> sink = new ArrayList<Integer>();
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			if(i < nVert/2) {
				source.add(i);
			}
			else {
				sink.add(i);
			}
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			graph.addEdge(source.get(i),sink.get(i));
		}
		
		for(int  i = 0 ; i < nVert/2 ; i++) {
			ArrayList<Integer> edgeList = new ArrayList<Integer>(graph.getOutgoingVertices(i));
			assertTrue(edgeList.size()==1);
			assertTrue(edgeList.get(0)==sink.get(i));
		}
	}

	@Test
	public void testGetIncomingVertices() {
		int nVert = 10;

		ArrayList<Integer> source = new ArrayList<Integer>();
		ArrayList<Integer> sink = new ArrayList<Integer>();
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			if(i < nVert/2) {
				source.add(i);
			}
			else {
				sink.add(i);
			}
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			graph.addEdge(source.get(i),sink.get(i));
		}
		
		for(int  i = 0 ; i < nVert/2 ; i++) {
			ArrayList<Integer> edgeList = new ArrayList<Integer>(graph.getOutgoingVertices(i));
			assertTrue(edgeList.size()==1); // check the right about of edges 
			assertTrue(edgeList.get(0)==sink.get(i)); // check that the edge was formed correctly and  
		}
	}

	@Test
	public void testGetOutgoingEdges() {
		int nVert = 10;

		ArrayList<Integer> source = new ArrayList<Integer>();
		ArrayList<Integer> sink = new ArrayList<Integer>();
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			if(i < nVert/2) {
				source.add(i);
			}
			else {
				sink.add(i);
			}
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			graph.addEdge(source.get(i),sink.get(i));
		}
		
		for(int  i = 0 ; i < nVert/2 ; i++) {
			ArrayList<DirectionalEdge<Integer>> edgeList = new ArrayList<DirectionalEdge<Integer>>(graph.getOutgoingEdges(i));
			assertTrue(edgeList.size()==1); // check the right about of edges 
			assertTrue(edgeList.get(0).getSink()==sink.get(i)); // check that the edge was formed correctly and 
			
		}
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 10;

		ArrayList<Integer> source = new ArrayList<Integer>();
		ArrayList<Integer> sink = new ArrayList<Integer>();
		SimpleAdjacencyListDiGraph<Integer> graph = new SimpleAdjacencyListDiGraph<Integer>();
		for(int i = 0 ; i < nVert; i++) {
			if(i < nVert/2) {
				source.add(i);
			}
			else {
				sink.add(i);
			}
			graph.addVertex(i);
		}
		for(int i = 0 ; i < nVert/2; i++) {
			graph.addEdge(source.get(i),sink.get(i));
	
		}
		
		for(int  i = 0 ; i < nVert/2 ; i++) {
			ArrayList<DirectionalEdge<Integer>> edgeList = new ArrayList<DirectionalEdge<Integer>>(graph.getOutgoingEdges(i));
			assertTrue(edgeList.size()==1); // check the right about of edges 
			assertTrue(edgeList.get(0).getSink()==sink.get(i)); // check that the edge was formed correctly and 
		}
	}

	@Test
	public void testAddVertex() {
		int nVert = 10;
		int nVertUpper = 20; 
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
		for(int i = 0 ; i < nVertUpper ; i++) {
			graph.addVertex(i);
		}
		Set<Integer> vert = graph.getVertices();
		assertTrue(vert.size()==nVertUpper);
		for(int i = 0 ; i < nVertUpper ; i++) {
			assertTrue(vert.contains(i));
		}
	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
		Set<Integer> vert = graph.getVertices();
		assertTrue(vert.size()==nVert);
		for(int i = 0 ; i < nVert ; i++) {
			assertTrue(vert.contains(i));
		}
	}

	@Test
	public void testGetEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVert = 10;
		SimpleAdjacencyListDiGraph<Integer> graph = createCompleteGraph(nVert);
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
	public void testAddEdgeVV() {
		fail("Not yet implemented");
	}
	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	protected static SimpleAdjacencyListDiGraph<Integer> createCompleteGraph(int nVertices){
		SimpleAdjacencyListDiGraph<Integer > graph = new SimpleAdjacencyListDiGraph<Integer >();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		for (int i = 0; i < nVertices ; i++) {
			for (int j = 0; j < nVertices ; j++) {
				if(i!=j) {
					graph.addEdge(i,j);
					graph.addEdge(j,i);
				}
			}
		}
		return graph;
	}

}
