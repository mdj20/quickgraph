package com.mdj20.quickgraph.quickgraph.traversal.algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectionalEdge;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;

public class DikstrasTest {

	
	// creates a series of randomly generated graphs and tests the results of Dijkstra's methods against BelmanFord's 
	
	@Test
	public void testFindShortestPathInt() {
		
		int nTestIterations = 100000;
		int nVerts = 25; 
		int nEdges = 50;
		int lowerBoundWeight = 1;
		int upperBoundWeight = 25;
		Integer sourceIndex = null, sinkIndex = null;
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < nTestIterations ; i ++ ) {
			
			// create random graph, and pull a source and a sink for pathfinding searches
			WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedDiGraph(nVerts, nEdges, lowerBoundWeight, upperBoundWeight);
			ArrayList<Character> vertices = new ArrayList<Character>(graph.getVertices());
			sourceIndex = rando.nextInt(vertices.size());
			sinkIndex = rando.nextInt(vertices.size());
			Character source = null, sink = null;
			while(sourceIndex.equals(sinkIndex)) {
				sourceIndex = rando.nextInt(vertices.size());
				sinkIndex = rando.nextInt(vertices.size());
			}
			source = vertices.get(sourceIndex);
			sink = vertices.get(sinkIndex);
			
			// find paths
			List<WeightedDirectionalEdge<Character,Integer>> dEdges = Dikstras.findShortestPathInt(graph, source, sink);
			List<WeightedDirectionalEdge<Character,Integer>> bmEdges = BellmanFord.findShortestPathInt(graph, source, sink);
			
			// tests results
			boolean exactMatch = checkEdgeListEquality(dEdges,bmEdges); // checks for and exact match
			
			// BellmanFord and Dikstras might return slightly diffrent results if they find multiple shortest paths that are equal weight.
			if(!exactMatch) {
					exactMatch = checkEdgeListWeightIntTotal(dEdges,bmEdges); // checks for a weight sum match, 
				}
			assertTrue(exactMatch);
			}
	}

	@Test
	public void testFindShortestPathDouble() {
		int nTestIterations = 100000;
		int nVerts = 25; 
		int nEdges = 50;
		int lowerBoundWeight = 1;
		int upperBoundWeight = 25;
		Integer sourceIndex = null, sinkIndex = null;
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < nTestIterations ; i ++ ) {
			
			// create random graph, and pull a source and a sink for pathfinding searches
			WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedDiGraph(nVerts, nEdges, lowerBoundWeight, upperBoundWeight);
			ArrayList<Character> vertices = new ArrayList<Character>(graph.getVertices());
			sourceIndex = rando.nextInt(vertices.size());
			sinkIndex = rando.nextInt(vertices.size());
			Character source = null, sink = null;
			while(sourceIndex.equals(sinkIndex)) {
				sourceIndex = rando.nextInt(vertices.size());
				sinkIndex = rando.nextInt(vertices.size());
			}
			source = vertices.get(sourceIndex);
			sink = vertices.get(sinkIndex);
			
			// find paths
			List<WeightedDirectionalEdge<Character,Integer>> dEdges = Dikstras.findShortestPathDouble(graph, source, sink);
			List<WeightedDirectionalEdge<Character,Integer>> bmEdges = BellmanFord.findShortestPathDouble(graph, source, sink);
			
			// tests results
			boolean exactMatch = checkEdgeListEquality(dEdges,bmEdges); // checks for and exact match
			
			// BellmanFord and Dikstras might return slightly different results if they find multiple shortest paths that are equal weight.
			if(!exactMatch) {
					exactMatch = checkEdgeListWeightIntTotal(dEdges,bmEdges); // checks for a weight sum match, 
				}
			assertTrue(exactMatch);
			}
	}
	
	// tests for List<WeightedDirectedEdge<Character,Integer>> equality returns true if equal
	private boolean checkEdgeListEquality(List<WeightedDirectionalEdge<Character, Integer>> A, List<WeightedDirectionalEdge<Character, Integer>> B) {
		boolean ret = true;
		if(A.size()!=B.size()) {
			return false;
		}
		else {
			for (int i = 0 ; i < A.size() ; i++) {
				if(!A.get(i).equals(B.get(i))) {
					return true;
				}
			}
		}
		return ret;
	}
	
	
	// tests for edgeList integer sum equivalence  returns true if sums are equal.
	private boolean checkEdgeListWeightIntTotal(List<WeightedDirectionalEdge<Character, Integer>> A, List<WeightedDirectionalEdge<Character, Integer>> B) {
		int sumA = 0, sumB = 0;
		for(WeightedDirectionalEdge<Character, Integer> wde: A) {
			sumA += wde.getWeight();
		}
		for(WeightedDirectionalEdge<Character, Integer> wde: B) {
			sumB += wde.getWeight();
		}
		return sumA == sumB;
	}

}
