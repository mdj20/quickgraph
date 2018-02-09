package com.mdj20.quickgraph.quickgraph.testutilities;

import java.util.Random;

import com.mdj20.quickgraph.quickgraph.main.AdjListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.AdjListGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjListGraph;



/*
 * testing class, graph builder. 
 */ 

public class FastGraphBuilder {

	
	public static WeightedAdjListDiGraph<Character,Integer> getWeightedDiGraph(){
		return getWeightedDiGraph(TestGraphData.TestGraph0);
	}
	public static WeightedAdjListGraph<Character,Integer> getWeightedGraph(){
		return getWeightedGraph(TestGraphData.TestGraph0);
	}
	public static WeightedAdjListDiGraph<Character,Integer> getWeightedDiGraph(TestGraphData testGraphData){
		return buildWeightedDiGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink(),testGraphData.getWeights());
	}
	public static WeightedAdjListGraph<Character,Integer> getWeightedGraph(TestGraphData testGraphData){
		return buildWeightedGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink(),testGraphData.getWeights());
	}
	public static AdjListDiGraph<Character> getSimpleDiGraph(TestGraphData testGraphData){
		return buildSimpleDiGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink());
	}
	public static AdjListGraph<Character> getSimpleGraph(TestGraphData testGraphData){
		return buildSimpleGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink());
	}
	

	public static <V,W extends Number & Comparable<W>> WeightedAdjListDiGraph<V,W> buildWeightedDiGraph(V vertex[], V edgeS[], V edgeE[], W weights[]){
		if(edgeS.length != edgeS.length && edgeS.length != weights.length) {
			throw new IllegalArgumentException("Lengths of edge and weight arrays, must equal");
		}
		WeightedAdjListDiGraph<V,W> graph = new WeightedAdjListDiGraph<V,W>();
		for(V c: vertex) {
			graph.addVertex(c);
		}
		for(int i = 0 ; i < weights.length ; i++) {
			graph.addEdge(edgeS[i], edgeE[i], weights[i]);
		}
		return graph;
	}
	
	public static WeightedAdjListDiGraph<Character,Integer> buildRandomWeightedDiGraph(int v, int e, int lowerBoundWeight, int upperBoundWeight ){
		int range = 0,offset = 0;
		if (lowerBoundWeight <= upperBoundWeight) {
			range = upperBoundWeight-lowerBoundWeight;
			offset = lowerBoundWeight;
		}
		else if (upperBoundWeight<=lowerBoundWeight) {
			range = lowerBoundWeight-upperBoundWeight;
			offset = upperBoundWeight;
		}
		
		WeightedAdjListDiGraph<Character,Integer> graph = new WeightedAdjListDiGraph<Character,Integer>();
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i< v; i++ ) {
			graph.addVertex(tc(i));
		}
		int source, sink, weight;
		for(int i = 0 ; i<e ; i++) {
			source = rando.nextInt(v);
			sink = rando.nextInt(v);
			weight = rando.nextInt(range)+offset;
			graph.addEdge(tc(source), tc(sink), weight);
		}
		return graph;
	}
	
	public static WeightedAdjListGraph<Character,Integer> buildRandomWeightedGraph(int numVetecies, int numEdges,  int lowerBoundWeight, int upperBoundWeight ) {
		int range = 0,offset = 0;
		if (lowerBoundWeight <= upperBoundWeight) {
			range = upperBoundWeight-lowerBoundWeight;
			offset = lowerBoundWeight;
		}
		else if (upperBoundWeight<=lowerBoundWeight) {
			range = lowerBoundWeight-upperBoundWeight;
			offset = upperBoundWeight;
		}
		
		WeightedAdjListGraph<Character,Integer> graph = new WeightedAdjListGraph<Character,Integer>();
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i< numVetecies; i++ ) {
			graph.addVertex(tc(i));
		}
		int source, sink, weight;
		for(int i = 0 ; i<numEdges ; i++) {
			source = rando.nextInt(numVetecies);
			sink = rando.nextInt(numVetecies);
			weight = rando.nextInt(range)+offset;
			graph.addEdge(tc(source), tc(sink), weight);
		}
		return graph;
	}
	
	public static <V, W> WeightedAdjListGraph<V,W> buildWeightedGraph(V vert[], V source[], V sink[], W weight[]){
		WeightedAdjListGraph<V,W> graph = new WeightedAdjListGraph<V,W>();
		for(V v:vert) {
			graph.addVertex(v);
		}
		for(int i=0;i<source.length;i++) {
			graph.addEdge(source[i], sink[i], weight[i]);
		}
		return graph;
	}
	
	public static <V> AdjListGraph<V> buildSimpleGraph(V vert[], V source[], V sink[]){
		if(source.length != sink.length) {
			throw new IllegalArgumentException("Lengths of souce and sink arrays must equal");
		}
		AdjListGraph<V> graph = new AdjListGraph<V>();
		for(V v: vert) {
			graph.addVertex(v);
		}
		for(int i = 0 ; i<source.length; i++) {
			graph.addEdge(source[i],sink[i]);
		}
		return graph;
	}
	public static <V> AdjListDiGraph<V> buildSimpleDiGraph(V vert[], V source[], V sink[]){
		if(source.length != sink.length) {
			throw new IllegalArgumentException("Lengths of souce and sink arrays must equal");
		}
		AdjListDiGraph<V> graph = new AdjListDiGraph<V>();
		for(V v: vert) {
			graph.addVertex(v);
		}
		for(int i = 0 ; i<source.length; i++) {
			graph.addEdge(source[i],sink[i]);
		}
		return graph;
	}
	
	// int to char convenience method
	public static char tc(int i) {
		if (i > 25){
			throw new IllegalArgumentException("i must be between 0 and 25");
		}
		return (char) (i+65);
	}
	public static int[] fillArrayRandom(int array[], int upperBound, int offset){
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < array.length; i++){
			array[i] = rando.nextInt(upperBound)+offset;
		}
		return array;
	}
	public static char[] fillArray(char array[]) {
		for (int i =0; i<array.length ; i++) {
			array[i] = tc(i);
		}
		return array;
	}
	public static int[] fillArray(int array[], int offset) {
		for(int i = 0; i< array.length; i++) {
			array[i] = i+offset;
		}
		return array;
	}
	
	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	public static AdjListGraph<Integer> completeSimpleIntegerGraph(int nVertices){
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
	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	public static WeightedAdjListGraph<Integer,Integer> createSaturatedWeightedGraph(int nVertices){
		WeightedAdjListGraph<Integer,Integer> graph = new WeightedAdjListGraph<Integer,Integer>();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		
		for (int i = 0; i < nVertices ; i++) {
			for (int j = i; j < nVertices ; j++) {
				if(i!=j) {
					graph.addEdge(i,j,i-j);
				}
			}
		}
		return graph;
	}
	
}
