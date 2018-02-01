package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.DirectionalEdge;
import com.mdj20.quickgraph.quickgraph.main.SimpleWeightedDirectionalEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectionalEdge;

public class WeightedAdjacencyListDiGraphTest {

	@Test
	public void testAddEdgeVVW() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		testGraph.addVertex(0);  
		testGraph.addVertex(1);
		testGraph.addEdge(0,1,1);
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSet =  testGraph.getEdges();
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent0 =  testGraph.getConnectingEdges(0);
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent1 =  testGraph.getConnectingEdges(1);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		assertTrue(edgeSet.size()==1);  // check edges size == 0
		assertTrue(edgeSetAdjacent0.contains(edgeList.get(0)));  // check vertex 0 
		assertTrue(edgeSetAdjacent1.contains(edgeList.get(0))); // check vertex 1
	}

	@Test
	public void testAddEdgeE() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		testGraph.addVertex(0);  
		testGraph.addVertex(1);
		testGraph.addEdge(new SimpleWeightedDirectionalEdge<Integer,Integer>(0,1,1));
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSet =  testGraph.getEdges();
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent0 =  testGraph.getConnectingEdges(0);
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent1 =  testGraph.getConnectingEdges(1);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		assertTrue(edgeSet.size()==1);  // check edges size == 0
		assertTrue(edgeSetAdjacent0.contains(edgeList.get(0)));  // check vertex 0 
		assertTrue(edgeSetAdjacent1.contains(edgeList.get(0))); // check vertex 1
	}

	@Test
	public void testRemoveEdge() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(4);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		assertTrue(testGraph.getEdges().contains(edgeList.get(0)));
		testGraph.removeEdge(edgeList.get(0));
		assertTrue(!testGraph.getEdges().contains(edgeList.get(0)));
	}

	@Test
	public void testGetOutgoingVertices() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(4);
		Set<Integer> expected = new HashSet<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		Set<Integer> verts0 = testGraph.getOutgoingVertices(0);
		for(Integer i: expected) {
			assertTrue(verts0.contains(i));
		}
		assertTrue(expected.size()==verts0.size());
	}

	@Test
	public void testGetIncomingVertices() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(4);
		Set<Integer> expected = new HashSet<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		Set<Integer> verts0 = testGraph.getIncomingVertices(0);
		for(Integer i: expected) {
			assertTrue(verts0.contains(i));
		}
		assertTrue(expected.size()==verts0.size());
	}

	@Test
	public void testGetOutgoingEdges() {
		int nVerts = 4;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		Set<WeightedDirectionalEdge<Integer,Integer>> outgoing0 = testGraph.getOutgoingEdges(0);
		for(int i = 0 ; i<nVerts;i++) {
			assertTrue(testGraph.getOutgoingEdges(i).size()==nVerts-1); // checks size of edges
			for(WeightedDirectionalEdge<Integer,Integer> wde: testGraph.getOutgoingEdges(i)) {
				assertTrue(i-wde.getSink() == wde.getWeight()); // checks weight	
				assertTrue(i == wde.getSource());
			}
		}
	}

	@Test
	public void testGetIncomingEdges() {
		int nVerts = 4;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		Set<WeightedDirectionalEdge<Integer,Integer>> incoming0 = testGraph.getIncomingEdges(0);
		for(int i = 0 ; i<nVerts;i++) {
			assertTrue(testGraph.getIncomingEdges(i).size()==nVerts-1); // checks size of edges
			for(WeightedDirectionalEdge<Integer,Integer> wde: testGraph.getIncomingEdges(i)) {
				assertTrue(wde.getSource()-i == wde.getWeight()); // checks weight	
				assertTrue(i == wde.getSink());  // checks edge endpoint
			}
		}
	}

	@Test
	public void testAddVertex() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		int n = 100;
		Random rando = new Random(System.nanoTime());
		Set<Integer> expected = new HashSet<Integer>();
		for(int i = 0 ; i<n ; i++ ) {
			int value = rando.nextInt(n);
			testGraph.addVertex(value);
			expected.add(value);
		}
		assertTrue(testGraph.getVertices().size()==expected.size());
		for(Integer i: expected) {
			assertTrue(testGraph.getVertices().contains(i));
		}
	}

	@Test
	public void testRemoveVertex() {
		int nVerts = 4;
		int removedVertex = nVerts-1;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		testGraph.removeVertex(nVerts-1);
		assertTrue(!testGraph.getVertices().contains(nVerts-1)); // test if vertex is removed...
		for(int i = 0 ; i <nVerts-1 ; i++) {
			Set<Integer> neighbors = testGraph.getAdjacentVertices(i);
			//System.out.println(neighbors.contains(removedVertex));
			assertTrue(!neighbors.contains(removedVertex));
		}
		Set<WeightedDirectionalEdge<Integer, Integer>> edgeList = testGraph.getEdges();
		for(DirectionalEdge<Integer> de: edgeList){
			assertTrue(!(de.getSource().equals(nVerts-1)||de.getSink().equals(nVerts-1)));
		}
	}

	@Test
	public void testGetVertices() {
		int nVerts = 10;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		Set<Integer> vertices = testGraph.getVertices();
		assertTrue(vertices.size()==nVerts);
		for(int i = 0 ; i < nVerts;i++){
			assertTrue(vertices.contains(i));
		}
	}

	@Test
	public void testGetEdges() {
		int nVerts = 5;
		int nEdges= 0;
		for(int i = nVerts-1; i > 0; i--){
			nEdges+=i;
		}
		nEdges*=2;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		assertTrue(testGraph.getEdges().size()==nEdges);
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVerts = 5;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		for(int i = 0 ; i < nVerts; i++){
			Set<Integer> adjVerts = testGraph.getAdjacentVertices(i);
			for(int j = 0 ; j < nVerts; j++){
				if (j!=i){
					assertTrue(adjVerts.contains(j));
				}
				else{
					assertTrue(!adjVerts.contains(j));
				}
			}
		}
	}

	@Test
	public void testGetConnectingEdges() {
		int nVerts = 5;
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(nVerts);
		for(int i = 0 ; i < nVerts; i++){
			Set<WeightedDirectionalEdge<Integer, Integer>> connEdge = testGraph.getConnectingEdges(i);
			for(WeightedDirectionalEdge<Integer, Integer> wde: connEdge){
				if(wde.getSource().equals(i)){
					assertTrue(wde.getWeight()==i-wde.getSink());
				}
				else if (!wde.getSource().equals(i)&&wde.getSink().equals(i)){
					assertTrue(wde.getWeight()==wde.getSource()-i);
				}
				else{
					fail("testGetConnectingEdges fail at edge: "+wde.getSource() +" "+wde.getSink());
				}
			}
		}
	}

	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	protected static WeightedAdjacencyListDiGraph<Integer,Integer> createSaturatedGraph(int nVertices){
		WeightedAdjacencyListDiGraph<Integer,Integer> graph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		
		for (int i = 0; i < nVertices ; i++) {
			for (int j = 0; j < nVertices ; j++) {
				if(i!=j) {
					graph.addEdge(i,j,i-j);
				}
			}
		}
		return graph;
	}
	
}
