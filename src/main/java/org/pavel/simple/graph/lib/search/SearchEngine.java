package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchEngine<V> {

    List<Edge<V>> getPath(V src, V dst, Map<V, Set<Edge<V>>> vertexesToEdges);

}
