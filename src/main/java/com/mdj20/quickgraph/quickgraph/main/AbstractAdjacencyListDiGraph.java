package com.mdj20.quickgraph.quickgraph.main;
import java.util.HashSet;
import java.util.Set;

/**Abstract base class used in the DiGraph object hierarchy.
 * <p>
 * 
 * @author Matthew D. Jeffreys
 * 
 * @param <V> Vertex type
 * @param <E> Edge type
 */

public abstract class AbstractAdjacencyListDiGraph<V,E extends DirectionalEdge<V>> extends AbstractAdjacencyListGraph<V,E> implements DiGraph<V,E> {

	AbstractAdjacencyListDiGraph() {
		super(GraphType.DIRECTED);
	}
	
	AbstractAdjacencyListDiGraph(GraphParameters parameters){
		super(parameters);
	}

	@Override
	public boolean addEdge(E edge) {
		boolean ret = false;
		if (checkVertices(edge)) {
			addEdgeToGraph(edge.getSource(),edge);
			edges.add(edge);
			ret = true;
		}
		return ret;
	}
	
	
	@Override
	public void removeEdge(E edge) {
		if (checkVertices(edge)) {
			removeEdgeFromGraph(edge.getSource(),edge);
			edges.remove(edge);
		}
	}
	
	// Override is necessary to remove incoming edges in DiGraph implementations 
	@Override 
	public void removeVertex(V vertex) {
		if(graph.containsKey(vertex)) {
			Set<E> incomingEdges = this.getIncomingEdges(vertex);
			edges.removeAll(graph.get(vertex));	// remove outgoing edges
			for(E edge: incomingEdges) {  // remove incoming edges
				removeEdge(edge);
			}
			graph.remove(vertex); 
		}
	}

	@Override
	public Set<V> getOutgoingVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		if(graph.containsKey(vertex)) {
			for(Edge<V> e: graph.get(vertex)) {
				ret.add( e.getOpposingVertex(vertex));
			}
		}
		return ret;
	}

	@Override
	public Set<V> getIncomingVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		if( graph.containsKey(vertex) ) {
			for(DirectionalEdge<V> d: edges){
				if(vertex.equals(d.getSink())) {
					ret.add(d.getSource());
				}
			}
		}
		return ret;
	}

	@Override
	public Set<E> getOutgoingEdges(V vertex) {
		if(graph.containsKey(vertex)) {
			return graph.get(vertex);
		}
		else 
			return new HashSet<E>();
	}

	@Override
	public Set<E> getIncomingEdges(V vertex) {
		HashSet<E> ret = new HashSet<E>();
		if(graph.containsKey(vertex)) {
			for(E d: edges){
				if(vertex.equals(d.getSink())) {
					ret.add(d);
				}
			}
		}
		return ret;
	}
	
	@Override 
	public Set<E> getConnectingEdges(V vertex){
		Set<E> incoming = this.getIncomingEdges(vertex);
		incoming.addAll(this.getOutgoingEdges(vertex));
		return incoming;
	}

}