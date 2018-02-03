package com.mdj20.quickgraph.quickgraph.traversal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListDiGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedAdjacencyListGraph;
import com.mdj20.quickgraph.quickgraph.main.WeightedDirectionalEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedEdge;
import com.mdj20.quickgraph.quickgraph.main.WeightedGraph;
import com.mdj20.quickgraph.quickgraph.testutilities.FastGraphBuilder;
import com.mdj20.quickgraph.quickgraph.testutilities.TestGraphData;

public class WeightedPathFinderTest {

	@Test
	public void testBellmanFordIntPath() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		Character source = TestGraphData.TestGraph0.getVerticies()[0];
		Character sink = TestGraphData.TestGraph0.getVerticies()[2];
		WeightedPathFinder<Character, WeightedEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(graph);
		Path<Character,WeightedEdge<Character,Integer>> bmPath = pf.bellmanFordIntPath(source, sink);
		Path<Character,WeightedEdge<Character,Integer>> dPath = pf.dikstrasIntPath(source, sink);
		
		assertTrue(weightedPathEqualorSum(bmPath,dPath));		
	}

	@Test
	public void testBellmanFordDoublePath() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph0);
		Character source = TestGraphData.TestGraph0.getVerticies()[0];
		Character sink = TestGraphData.TestGraph0.getVerticies()[2];
		WeightedPathFinder<Character, WeightedEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(graph);
		Path<Character,WeightedEdge<Character,Integer>> bmPath = pf.bellmanFordDoublePath(source, sink);
		Path<Character,WeightedEdge<Character,Integer>> dPath = pf.dikstrasDoublePath(source, sink);
		
		assertTrue(weightedPathEqualorSum(bmPath,dPath));
	}

	@Test
	public void testDikstrasIntPath() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph1);
		Character source = TestGraphData.TestGraph1.getVerticies()[0];
		Character sink = TestGraphData.TestGraph1.getVerticies()[2];
		
		WeightedPathFinder<Character, WeightedEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(graph);
		Path<Character,WeightedEdge<Character,Integer>> bmPath = pf.bellmanFordIntPath(source, sink);
		Path<Character,WeightedEdge<Character,Integer>> dPath = pf.dikstrasIntPath(source, sink);
		
		assertTrue(weightedPathEqualorSum(bmPath,dPath));
	}

	@Test
	public void testDikstrasDoublePath() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.getWeightedGraph(TestGraphData.TestGraph1);
		Character source = TestGraphData.TestGraph1.getVerticies()[0];
		Character sink = TestGraphData.TestGraph1.getVerticies()[2];
		
		WeightedPathFinder<Character, WeightedEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(graph);
		Path<Character,WeightedEdge<Character,Integer>> bmPath = pf.bellmanFordDoublePath(source, sink);
		Path<Character,WeightedEdge<Character,Integer>> dPath = pf.dikstrasDoublePath(source, sink);
		
		assertTrue(weightedPathEqualorSum(bmPath,dPath));
	}

	@Test
	public void testDepthFirstEdgeList() {
		WeightedAdjacencyListDiGraph<Character,Integer> testGraph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		WeightedPathFinder<Character, WeightedDirectionalEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(testGraph);
		Character source = TestGraphData.TestGraph1.getVerticies()[2];
		Character sink = TestGraphData.TestGraph1.getVerticies()[0];
		List<WeightedDirectionalEdge<Character,Integer>> edgeList = pf.depthFirstEdgeList(source, sink);
		//int sourceIndex = edgeList.get(0).indexOf(source);
		assertTrue(traceEdgeList(edgeList,source,sink));
	}

	@Test
	public void testBreadthFirstEdgeList() {
		WeightedAdjacencyListDiGraph<Character,Integer> testGraph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		WeightedPathFinder<Character, WeightedDirectionalEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(testGraph);
		Character source = TestGraphData.TestGraph1.getVerticies()[2];
		Character sink = TestGraphData.TestGraph1.getVerticies()[0];
		List<WeightedDirectionalEdge<Character,Integer>> edgeList = pf.breadthFirstEdgeList(source, sink);
		assertTrue(traceEdgeList(edgeList,source,sink));
	}

	@Test
	public void testDepthFirstPath() {
		WeightedAdjacencyListDiGraph<Character,Integer> testGraph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		WeightedPathFinder<Character, WeightedDirectionalEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(testGraph);
		Character source = TestGraphData.TestGraph1.getVerticies()[2];
		Character sink = TestGraphData.TestGraph1.getVerticies()[0];
		Path<Character, WeightedDirectionalEdge<Character, Integer>> edgeList = pf.depthFirstPath(source, sink);
		assertTrue(traceEdgeList(edgeList.getEdgeList(),source,sink));
	}

	@Test
	public void testBreadthFirstPath() {
		WeightedAdjacencyListDiGraph<Character,Integer> testGraph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		WeightedPathFinder<Character, WeightedDirectionalEdge<Character, Integer>, Integer>  pf = WeightedPathFinder.getWeightedPathFinder(testGraph);
		Character source = TestGraphData.TestGraph1.getVerticies()[2];
		Character sink = TestGraphData.TestGraph1.getVerticies()[0];
		Path<Character, WeightedDirectionalEdge<Character, Integer>> path = pf.breadthFirstPath(source, sink);
		assertTrue(traceEdgeList(path.getEdgeList(),source,sink));
	}
	
	private <V,E extends Edge<V>> boolean testWeightedPathEquality(Path<V,E> A, Path<V,E> B){
		boolean ret = true;
		return ret;
	}
	
	// checks to paths and determines if the sume of their respective weights is equal.
	private <V,E extends WeightedEdge<V,W>,W extends Number> boolean weightSumEqualInt(Path<V,E> A, Path<V,E> B){
		int a=0, b=0;
		a = pathWeightSumInt(A);
		b = pathWeightSumInt(B);
		return a==b;
	}
	
	private <V,E extends WeightedEdge<V,W>,W extends Number> boolean weightedPathEqualorSum(Path<V,E> A, Path<V,E> B){
		boolean ret= true;
		//check for direct equality
		if(A.getEdgeList().size() == B.getEdgeList().size()){
			List<E> aList = A.getEdgeList();
			List<E> bList = B.getEdgeList();
			for(int i = 0 ; i < A.getEdgeList().size() ; i++){
				ret = aList.get(i).equals(bList.get(i));
				if(!ret){
					break;
				}
			}
		}
		// if not directly equal, check weight sums equality
		if(!ret){
			ret = weightSumEqualInt(A,B);
		}
		return ret;
	}
	
	private <V,E extends WeightedEdge<V,W>,W extends Number> int pathWeightSumInt(Path<V,E> path){
		int ret = 0;
		for(E e: path.getEdgeList()){
			ret += e.getWeight().intValue();
		}
		return ret;
	}
	
	private <E extends Edge<V>,V> boolean traceEdgeList(List<E> list, V source, V sink){
		boolean ret = true;
		
		if( list.size() == 0 ){
			ret = false;
		}
		if ( ret ){
			int index = 0;
			E first = list.get(0);
			E last = list.get(list.size()-1);
			V temp = first.getOpposingVertex(source);
			while ( !temp.equals(sink) && index != list.size()-1) {
				index++;
				first = list.get(index);
				temp  = first.getOpposingVertex(temp);
			}
			if(temp.equals(sink)){
				ret = true;
			}
		}
		return ret;
	}

}
