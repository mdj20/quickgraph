package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.DefaultDirectedEdge;
import com.mdj20.quickgraph.quickgraph.main.DiGraph;
import com.mdj20.quickgraph.quickgraph.main.DirectedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubDiGraphTest {

	@Test
	public void testAddVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		subGraph.addVertex(added);
		Set<Integer> subGraphVerts = subGraph.getVertices();
		Set<Integer> graphVerts = graph.getVertices();
		assertTrue(subGraphVerts.contains(added));
		assertTrue(graphVerts.contains(added));
	}

	@Test
	public void testAddEdgeE() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		DefaultDirectedEdge<Integer> edge = new DefaultDirectedEdge<Integer>(0,1);
		subGraph.addEdge(edge);
		Set<DirectedEdge<Integer>> subEdges = subGraph.getEdges();
		Set<DirectedEdge<Integer>> parentEdges = graph.getEdges();
		assertTrue(subEdges.contains(edge));
		assertTrue(parentEdges.contains(edge));
	}

	@Test
	public void testAddEdgeVV() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		DirectedEdge<Integer> edge = subGraph.addEdge(0, 1);
		subGraph.addEdge(edge);
		Set<DirectedEdge<Integer>> subEdges = subGraph.getEdges();
		Set<DirectedEdge<Integer>> parentEdges = graph.getEdges();
		assertTrue(subEdges.contains(edge));
		assertTrue(parentEdges.contains(edge));
	}

	@Test
	public void testRemoveVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int removed = subVertexLimit-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		subGraph.removeVertex(removed);
		Set<Integer> subGraphVerts = subGraph.getVertices();
		Set<Integer> graphVerts = graph.getVertices();
		assertTrue(!subGraphVerts.contains(removed));
		assertTrue(!graphVerts.contains(removed));
	}

	@Test
	public void testRemoveEdge() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		ArrayList<DirectedEdge<Integer>> subEdgeList = new ArrayList<DirectedEdge<Integer>>(subGraph.getEdges());
		DirectedEdge<Integer> removed = subEdgeList.get(0);
		subGraph.removeEdge(removed);
		Set<DirectedEdge<Integer>> subEdges = subGraph.getEdges();
		Set<DirectedEdge<Integer>> parentEdges = graph.getEdges();
		assertTrue(!subEdges.contains(removed));
		assertTrue(!parentEdges.contains(removed));
	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		Set<Integer> subVertexGraph = subGraph.getVertices();
		assertTrue(subVertexGraph.containsAll(subVertex));
	}

	@Test
	public void testGetEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex);
		Set<DirectedEdge<Integer>> subEdges = subGraph.getEdges();
		ArrayList<DirectedEdge<Integer>> subEdgeList = new ArrayList<DirectedEdge<Integer>>(subEdges);
		for(DirectedEdge<Integer> edge : subEdgeList){
			assertTrue(subGraph.getVertices().contains(edge.getVertex(0))&&subGraph.getVertices().contains(edge.getVertex(1)));
		}
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		DiGraph<Integer> graph = FastGraphBuilder.createCompleteDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubDiGraph<Integer> subGraph = new SubDiGraph<Integer>(graph,subVertex); 
		Set<Integer> adjacent = subGraph.getAdjacentVertices(tested);
		boolean expected[] = new boolean[subVertexLimit],  actual[] = new boolean[subVertexLimit];
		Arrays.fill(actual, false);
		Arrays.fill(expected, true);
		expected[tested] = false;
		for(Integer i : adjacent){
			actual[i]=true;
		}
		assertArrayEquals(expected,actual);
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
