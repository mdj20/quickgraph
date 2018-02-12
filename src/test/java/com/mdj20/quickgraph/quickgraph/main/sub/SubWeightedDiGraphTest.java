package com.mdj20.quickgraph.quickgraph.main.sub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.DefaultWeightedDirectedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListDiGraph;

import com.mdj20.quickgraph.quickgraph.main.WeightedDirectedEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class SubWeightedDiGraphTest {

	@Test
	public void testAddVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int added = nVert-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		subGraph.addVertex(added);
		assertTrue(subGraph.getVertices().contains(added));
	}

	@Test
	public void testAddEdgeE() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		WeightedDirectedEdge<Integer,Integer> edge = new DefaultWeightedDirectedEdge<Integer,Integer>(0,1,1);
		subGraph.addEdge(edge);
		assertTrue(subGraph.getEdges().contains(edge));
		assertTrue(graph.getEdges().contains(edge));
	}

	@Test
	public void testAddEdgeVV() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		WeightedDirectedEdge<Integer,Integer> edge = subGraph.addEdge(0, 1);
		subGraph.addEdge(edge);
		assertTrue(subGraph.getEdges().contains(edge));
		assertTrue(graph.getEdges().contains(edge));
	}

	@Test
	public void testRemoveVertex() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int removed = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		subGraph.removeVertex(removed);
		assertTrue(!subGraph.getVertices().contains(removed));
		assertTrue(!graph.getVertices().contains(removed));
	}

	@Test
	public void testRemoveEdge() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		ArrayList<WeightedDirectedEdge<Integer,Integer>> subEdges = new ArrayList<WeightedDirectedEdge<Integer,Integer>>(subGraph.getEdges());
		WeightedDirectedEdge<Integer,Integer> edge = subEdges.get(0);
		subGraph.removeEdge(edge);
		assertTrue(!subGraph.getEdges().contains(edge));
		assertTrue(!graph.getEdges().contains(edge));
	}

	@Test
	public void testGetVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		boolean expected[] = new boolean[subVertex.size()], actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		for(Integer i : subGraph.getVertices()) {
			actual[i] = true;
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		ArrayList<WeightedDirectedEdge<Integer,Integer>> subEdges = new ArrayList<WeightedDirectedEdge<Integer,Integer>>(subGraph.getEdges());
		boolean expected[] = new boolean[subEdges.size()], actual[] = new boolean[subEdges.size()];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		for(int i = 0 ; i < subEdges.size() ; i++) {
			WeightedDirectedEdge<Integer,Integer>  edge = subEdges.get(i);
			if(subVertex.contains(edge.getSink()) && subVertex.contains(edge.getSource())){
				actual[i] = true;
			}
		}
		assertArrayEquals(actual,expected);
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		boolean expected[] = new boolean[subVertex.size()], actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		expected[tested]=false;
		Arrays.fill(actual, false);
		
		for(Integer i : subGraph.getAdjacentVertices(tested)) {
			if(subVertex.contains(i)) {
				actual[i] = true;
			}
			else{
				fail("vertex not in subGraph");
			}
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetConnectingEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		boolean expectedIn[] = new boolean[subVertex.size()];
		boolean actualIn[] = new boolean[subVertex.size()];
		Arrays.fill(expectedIn, true);
		Arrays.fill(actualIn, false);
		expectedIn[tested] = false;
		boolean expectedOut[] = new boolean[subVertex.size()];
		boolean actualOut[] = new boolean[subVertex.size()];
		Arrays.fill(expectedOut, true);
		Arrays.fill(actualOut, false);
		expectedOut[tested] = false;
		
		for(WeightedDirectedEdge<Integer,Integer> edge : subGraph.getConnectingEdges(tested)) {
			if(edge.getSource().equals(tested)) {
				actualOut[edge.getSink()]=true;
			}
			else if (edge.getSink().equals(tested)){
				actualIn[edge.getSource()] = true;
			}
			else {
				fail("edge doesn't connect to tested");
			}
		}
		assertArrayEquals(expectedIn,actualIn);
		assertArrayEquals(expectedOut,actualOut);
	}

	@Test
	public void testGetOutgoingVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		boolean expected[] = new boolean[subVertex.size()], actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		expected[tested]=false;
		Arrays.fill(actual, false);
		
		for(Integer i : subGraph.getOutgoingVertices(tested)) {
			if(subVertex.contains(i)) {
				actual[i] = true;
			}
			else{
				fail("vertex not in subGraph");
			}
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetIncomingVertices() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		boolean expected[] = new boolean[subVertex.size()], actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		expected[tested]=false;
		Arrays.fill(actual, false);
		
		for(Integer i : subGraph.getIncomingVertices(tested)) {
			if(subVertex.contains(i)) {
				actual[i] = true;
			}
			else{
				fail("vertex not in subGraph");
			}
		}
		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetOutgoingEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		
		boolean expected[] = new boolean[subVertex.size()];
		boolean actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		expected[tested] = false;
		
		for(WeightedDirectedEdge<Integer,Integer> edge : subGraph.getOutgoingEdges(tested)) {
			if(edge.getSource().equals(tested)) {
				actual[edge.getSink()]=true;
			}
			else {
				fail("edge doesn't connect to tested");
			}
		}

		assertArrayEquals(expected,actual);
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 10;
		int subVertexLimit = nVert/2;
		int tested = subVertexLimit-1;
		WeightedAdjListDiGraph<Integer,Integer> graph = FastGraphBuilder.createSaturatedWeightedDiGraph(nVert);
		HashSet<Integer> subVertex = new HashSet<Integer>();
		for(int i = 0 ; i < subVertexLimit ; i++) {
			subVertex.add(i);
		}
		SubWeightedDiGraph<Integer,Integer> subGraph = new SubWeightedDiGraph<Integer,Integer>(graph,subVertex);
		
		boolean expected[] = new boolean[subVertex.size()];
		boolean actual[] = new boolean[subVertex.size()];
		Arrays.fill(expected, true);
		Arrays.fill(actual, false);
		expected[tested] = false;
		
		for(WeightedDirectedEdge<Integer,Integer> edge : subGraph.getIncomingEdges(tested)) {
			if(edge.getSink().equals(tested)) {
				actual[edge.getSource()]=true;
			}
			else {
				fail("edge doesn't connect to tested");
			}
		}

		assertArrayEquals(expected,actual);
	}

}
