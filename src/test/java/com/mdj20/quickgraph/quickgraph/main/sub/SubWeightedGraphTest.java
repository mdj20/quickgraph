package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.DefaultWeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.Graph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubWeightedGraphTest {

	@Test
	public void testAddEdgeVVW() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		WeightedEdge<Integer,Integer> edge = subGraph.addEdge(0, 1, 1);
		assertTrue(subGraph.getEdges().contains(edge));
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
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		WeightedEdge<Integer,Integer> edge = new DefaultWeightedEdge<Integer,Integer>(0,1,1);
		assertTrue(subGraph.addEdge(edge));
		assertTrue(subGraph.getEdges().contains(edge));
	}

	@Test
	public void testRemoveVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int removed = subVertexLimit - 1;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		subGraph.removeVertex(removed);
		assertTrue(!subGraph.getVertices().contains(removed));
		assertTrue(!graph.getVertices().contains(removed));
	}

	@Test
	public void testRemoveEdge() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		ArrayList<WeightedEdge<Integer,Integer>> edgeList = new ArrayList<WeightedEdge<Integer,Integer>>(subGraph.getEdges());
		WeightedEdge<Integer,Integer> removedEdge = edgeList.get(0);
		subGraph.removeEdge(removedEdge);
		assertTrue(!subGraph.getEdges().contains(removedEdge));
		assertTrue(!graph.getEdges().contains(removedEdge));
	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		subVertex = subGraph.getVertices();
		
		assertTrue(subVertex.size()==subVertexLimit);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		// check if all edges are correct
		for(Integer i: subVertex) {
			actual[i] = true;
		}
		assertArrayEquals(actual,expected); // test 
	}

	@Test
	public void testGetEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		Set<WeightedEdge<Integer,Integer>> subEdges = subGraph.getEdges();
		subVertex = subGraph.getVertices();
		ArrayList<WeightedEdge<Integer,Integer>> subEdgeList = new ArrayList<WeightedEdge<Integer,Integer>>(subEdges);
		boolean[] expected = new boolean[subEdges.size()], actual = new boolean[subEdges.size()];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		for(int i = 0 ; i < subEdgeList.size() ; i++ ){
			WeightedEdge<Integer,Integer> edge = subEdgeList.get(i);
			if(subVertex.contains(edge.getVertex(0))&&subVertex.contains(edge.getVertex(1)))
				actual[i] = true;
		}
		assertArrayEquals(actual,expected);
	}

	@Test
	public void testGetAdjacentVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer,Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
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
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		Set<WeightedEdge<Integer,Integer>> subEdges = subGraph.getConnectingEdges(tested);
		subVertex = subGraph.getVertices();
		ArrayList<WeightedEdge<Integer,Integer>> subEdgeList = new ArrayList<WeightedEdge<Integer,Integer>>(subEdges);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		actual[tested] = true;
		for(int i = 0 ; i < subEdgeList.size() ; i++ ){
			WeightedEdge<Integer,Integer> edge = subEdgeList.get(i);
			actual[edge.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(actual,expected);
	}

	@Test
	public void testGetOutgoingVertices() {
		// setup
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer,Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
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
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer,Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
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
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		Set<WeightedEdge<Integer,Integer>> subEdges = subGraph.getOutgoingEdges(tested);
		subVertex = subGraph.getVertices();
		ArrayList<WeightedEdge<Integer,Integer>> subEdgeList = new ArrayList<WeightedEdge<Integer,Integer>>(subEdges);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		actual[tested] = true;
		for(int i = 0 ; i < subEdgeList.size() ; i++ ){
			WeightedEdge<Integer,Integer> edge = subEdgeList.get(i);
			actual[edge.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(actual,expected);
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = 0;
		WeightedAdjListGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedGraph(nVert);
		Set<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedGraph<Integer, Integer> subGraph = new SubWeightedGraph<Integer,Integer>(graph,subVertex);
		Set<WeightedEdge<Integer,Integer>> subEdges = subGraph.getIncomingEdges(tested);
		subVertex = subGraph.getVertices();
		ArrayList<WeightedEdge<Integer,Integer>> subEdgeList = new ArrayList<WeightedEdge<Integer,Integer>>(subEdges);
		boolean[] expected = new boolean[subVertexLimit], actual = new boolean[subVertexLimit];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		actual[tested] = true;
		for(int i = 0 ; i < subEdgeList.size() ; i++ ){
			WeightedEdge<Integer,Integer> edge = subEdgeList.get(i);
			actual[edge.getOpposingVertex(tested)] = true;
		}
		assertArrayEquals(actual,expected);
	}

}
