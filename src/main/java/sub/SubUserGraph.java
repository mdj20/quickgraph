package sub;

import java.util.Set;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.UserGraph;

public class SubUserGraph<V> extends AbstractSubGraph<UserGraph<V>, V, Edge<V>> implements UserGraph<V> {
	SubUserGraph(UserGraph<V> parentGraph, Set<V> vertices){
		super(parentGraph,vertices);
	}
}
