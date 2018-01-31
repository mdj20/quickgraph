package com.mdj20.quickgraph.quickgraph.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;

import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

public class WeightedAdjacencyListGraphTest {



	@Test
	public void testAddEdgeVVW() {
		int nEdge = 5;
		Random rando = new Random(System.nanoTime());
		
		// storage of the 
		ArrayList<Character> sourceList = new ArrayList<Character>();
		ArrayList<Character> sinkList = new ArrayList<Character>();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(10, 0, 0, 0);
		ArrayList<Character> verts = new ArrayList<Character>(graph.getVertices());
		Character source = null , sink = null;
		int weight=0;
		int nSuccess = 0;
		for(int i = 0 ; i < nEdge ; i++) {
			source = verts.get(rando.nextInt(verts.size()));
			sink = verts.get(rando.nextInt(verts.size()));
			weight = rando.nextInt(25);
			if(graph.addEdge(source, sink, weight)) {
				sourceList.add(source);
				sinkList.add(sink);
				weightList.add(weight);
				nSuccess++;
			}
		}

		int checked = 0;
		
		for( int i = 0 ; i < sourceList.size() ; i++) {
			Set<WeightedEdge<Character,Integer>> edges = graph.getConnectingEdges(sourceList.get(i));
			for(WeightedEdge<Character,Integer> wde: edges) {
				if(wde.getVertex(0).equals(sourceList.get(i)) && wde.getVertex(1).equals(sinkList.get(i)) ) {
					assertTrue(	wde.getWeight()==weightList.get(i));
					checked++;
					break;
				}
				else if(wde.getVertex(1).equals(sourceList.get(i)) && wde.getVertex(0).equals(sinkList.get(i))) {
					assertTrue(	wde.getWeight()==weightList.get(i));
					checked++;
					break;
				}
			}
		}
		assertTrue(checked==nSuccess);
	}

	@Test
	public void testAddVertex() {
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		HashSet<Character> refrence = new HashSet<Character>();
		Random rando = new Random(System.nanoTime());
		int nVerts = 15;
		int nSuccess = 0;
		while (nSuccess<nVerts) {
			Character c = FastGraphBuilder.tc(rando.nextInt(25));
			if(graph.addVertex(c)) {
				nSuccess++;
				refrence.add(c);
			}
			else {
				assertTrue(graph.getVertices().contains(c));  // check graph returns null if try to add vertex that already exists 
			}
		}
		for(Character c: refrence ) {
			assertTrue(graph.getVertices().contains(c)); // check if all verts that were successfully inserted, are returned in the list.
		}
		assertTrue(nSuccess==graph.getVertices().size());
	}

	@Test
	public void testAddEdgeE() {
		
	}

	@Test
	public void testRemoveVertex() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		Set<Character> verts = graph.getVertices();
		Character removed = verts.iterator().next();
		graph.removeVertex(removed);
		verts = graph.getVertices();
		assertTrue(!verts.contains(removed));
		for(Character c : graph.getVertices()) {
			for(WeightedEdge<Character,Integer> wde: graph.getConnectingEdges(c)) {
				assertTrue(!wde.getVertices().contains(removed));
			}
		}
	}

	@Test
	public void testRemoveEdge() {
		int nEdgesRemoved = 5;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		Set<WeightedEdge<Character,Integer>> edges = graph.getEdges();
		ArrayList<WeightedEdge<Character,Integer>> removedList = new ArrayList<WeightedEdge<Character,Integer>>();
		Iterator<WeightedEdge<Character,Integer>> itr = edges.iterator();
		if(edges.size() < nEdgesRemoved ) {
			fail("Not enough edges in graph to test");
		}
		for(int i = 0 ; i< nEdgesRemoved ; i++) {
			WeightedEdge<Character,Integer> current = itr.next();
			removedList.add(current);
			graph.removeEdge(current);
		}
		
		for(WeightedEdge<Character,Integer> wde : removedList) {
			assert(!graph.getEdges().contains(wde));
			for(Character c: wde.getVertices()) {
				assertTrue(!graph.getConnectingEdges(c).contains(wde));
			}
		}
		
		
	}

	@Test
	public void testGetVertices() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		for(Character c : TestGraphData.TestGraph0.getVerticies()) {
			assertTrue(graph.getVertices().contains(c));
		}
		assertTrue(graph.getVertices().size()==TestGraphData.TestGraph0.getVerticies().length);
	}

	@Test
	public void testGetEdges() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		Character source[] = TestGraphData.TestGraph0.getSource();
		Character sink[] = TestGraphData.TestGraph0.getSink();
		Integer weight[] = TestGraphData.TestGraph0.getWeights();
		boolean results[] = new boolean[source.length];
		Arrays.fill(results, false);
		for(WeightedEdge<Character,Integer> we: graph.getEdges()) {
			
		}
		assertTrue(graph.getVertices().size()==TestGraphData.TestGraph0.getVerticies().length);
	}

	@Test
	public void testGetAdjacentVertices() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<Character>> map = new HashMap<Character,HashSet<Character>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<Character>(graph.getAdjacentVertices(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(Character c2 : map.get(c)) {
				assertTrue(graph.getAdjacentVertices(c2).contains(c));
			}
		}
	}

	@Test
	public void testGetConnectingEdges() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<WeightedEdge<Character,Integer>>> map = new HashMap<Character,HashSet<WeightedEdge<Character,Integer>>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<WeightedEdge<Character,Integer>>(graph.getConnectingEdges(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(WeightedEdge<Character,Integer> we : map.get(c)) {
				assertTrue(graph.getAdjacentVertices(we.getOpposingVertex(c)).contains(c));
			}
		}
	}



	@Test
	public void testGetOutgoingVertices() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<Character>> map = new HashMap<Character,HashSet<Character>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<Character>(graph.getOutgoingVertices(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(Character c2 : map.get(c)) {
				assertTrue(graph.getOutgoingVertices(c2).contains(c));
			}
		}
	}

	@Test
	public void testGetIncomingVertices() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<Character>> map = new HashMap<Character,HashSet<Character>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<Character>(graph.getIncomingVertices(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(Character c2 : map.get(c)) {
				assertTrue(graph.getIncomingVertices(c2).contains(c));
			}
		}
	}

	@Test
	public void testGetOutgoingEdges() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<WeightedEdge<Character,Integer>>> map = new HashMap<Character,HashSet<WeightedEdge<Character,Integer>>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<WeightedEdge<Character,Integer>>(graph.getOutgoingEdges(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(WeightedEdge<Character,Integer> we : map.get(c)) {
				assertTrue(graph.getAdjacentVertices(we.getOpposingVertex(c)).contains(c));
			}
		}
	}

	@Test
	public void testGetIncomingEdges() {
		int nVert = 25;
		int nEdge = 35;
		int lowerW = 1;
		int upperW = 15;
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(nVert, nEdge, lowerW, upperW);
		HashMap<Character,HashSet<WeightedEdge<Character,Integer>>> map = new HashMap<Character,HashSet<WeightedEdge<Character,Integer>>>();
		for(Character c: graph.getVertices()) {
			map.put(c, new HashSet<WeightedEdge<Character,Integer>>(graph.getIncomingEdges(c)));
		}
		
		for(Character c: graph.getVertices()) {
			for(WeightedEdge<Character,Integer> we : map.get(c)) {
				assertTrue(graph.getAdjacentVertices(we.getOpposingVertex(c)).contains(c));
			}
		}
	}

	@Test
	public void testIsWeighted() {

		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		assertTrue(graph.isWeighted());
	}

	@Test
	public void testIsDirected() {
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		assertTrue(! graph.isDirected());
	}

}
