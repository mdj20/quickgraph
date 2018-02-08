package sub;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mdj20.quickgraph.quickgraph.main.AdjListGraph;
import com.mdj20.quickgraph.quickgraph.main.Edge;

public class SubUserGraphTest {

	@Test
	public void testAddVertex() {
		int nVert = 10;
		AdjListGraph<Integer> graph = completeSimpleIntegerGraph(10);
		Set<Integer> subSet  = new HashSet<Integer>();
		for(int i = 0 ; i < nVert/2 ; i++){
			subSet.add(i);
		}
		SubUserGraph<Integer> subGraph = new SubUserGraph<Integer>(graph,subSet);
		for(Integer i : subGraph.getVertices()){
			System.out.println(i);
		}
		for(Edge<Integer> e : subGraph.getEdges()){
			System.out.println(e.getVertex(0)+" "+e.getVertex(1));
		}
	}

	@Test
	public void testAddEdgeE() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEdgeVV() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentVertices() {
		fail("Not yet implemented");
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

	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by source - sink.
	protected static AdjListGraph<Integer> completeSimpleIntegerGraph(int nVertices){
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
	
	
}
