package sub;

import java.util.HashSet;
import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.BaseGraph;
import com.mdj20.quickgraph.quickgraph.main.Edge;


/**
 * Sub Graph decorator.
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> type of BaseGraph
 * @param <V> type of Vertex
 * @param <E> type of Edge
 */

public class AbstractSubGraph<G extends BaseGraph<V,E>,V,E extends Edge<V>> implements BaseGraph<V,E>{

	protected G parentGraph;
	protected Set<V> subVertex;
	protected Set<V> unmodifiableVertex;
	protected Set<E> subEdge;
	protected Set<E> unmodifiableEdge;

	// adds vertex to subgraph and adds vertex to parent graph if it isn't already a member.
	@Override
	public boolean addVertex(V vertex) {
		boolean ret = false;
		if(!parentGraph.getVertices().contains(vertex)) {
			ret = parentGraph.addVertex(vertex);
			if (ret) {
				subVertex.add(vertex);
			}
		}
		else if (!subVertex.contains(vertex)) {
			subVertex.add(vertex);
			ret = true;
		}
		return false;
	}

	@Override
	public boolean addEdge(E edge) {
		// TODO Auto-generated method stub
		return false;
	}

	// this is going to be a problem... how to determine weather to add an edge to a list or not...
	
	@Override
	public boolean addEdge(V vertex1, V vertex2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeVertex(V vertex) {
		if(checkSubVert(vertex)){
			subVertex.remove(vertex);
			removeAssociatedSubEdge(vertex);
		}
		
	}

	@Override
	public void removeEdge(E edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<V> getVertices() {
		return unmodifiableVertex;
	}

	@Override
	public Set<E> getEdges() {
		return unmodifiableEdge;
	}

	@Override
	public Set<V> getAdjacentVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getAdjacentVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getConnectingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimEdge(parentGraph.getConnectingEdges(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<V> getOutgoingVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getOutgoingVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<V> getIncomingVertices(V targetVertex) {
		Set<V> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimVert(parentGraph.getIncomingVertices(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getOutgoingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimEdge(parentGraph.getOutgoingEdges(targetVertex));
		}
		return ret;
	}

	@Override
	public Set<E> getIncomingEdges(V targetVertex) {
		Set<E> ret = null;
		if(checkSubVert(targetVertex)) {
			ret  = trimEdge(parentGraph.getIncomingEdges(targetVertex));
		}
		return ret;
	}
	
	protected boolean checkSubVert(V v) {
		return subVertex.contains(v);
	}
	protected boolean checkSubVert(V v1, V v2) {
		return checkSubVert(v1) && checkSubVert(v2);
	}
	
	
	protected Set<V> trimVert(Set<V> verts){
		Set<V> ret = new HashSet<V>();
		for(V v: verts) {
			if (subVertex.contains(v))
				ret.add(v);
		}
		return ret;
	}
	
	protected Set<E> trimEdge(Set<E> edges)	{
		Set<E> ret = new HashSet<E>();
		for(E e: edges) {
			if (subEdge.contains(e))
				ret.add(e);
		}
		return ret;
	}
	
	protected void removeAssociatedSubEdge(V v) {
		Set<E> removed = new HashSet<E>();
		for(E e : subEdge) {
			if(e.getVertices().contains(v)) {
				removed.add(e);
			}
		}
		subEdge.removeAll(removed);
	}
	protected void removedAssociatedSubEdge(V v1, V v2) {
		Set<E> removed = new HashSet<E>();
		for(E e : subEdge) {
			if(e.getVertices().contains(v1) || e.getVertices().contains(v2)) {
				removed.add(e);
			}
		}
		subEdge.removeAll(removed);		
	}
}
