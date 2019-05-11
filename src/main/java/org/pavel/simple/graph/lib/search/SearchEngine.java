package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Basic interface to be implemented by path search algorithm.
 *
 * @param <V> vertex type.
 */
public interface SearchEngine<V> {

    /**
     * Get path from source to the destination vertex with a provided mapping between vertices and edges.
     *
     * @param source          vertex.
     * @param destination     vertex.
     * @param verticesToEdges mapping between vertices and edges.
     * @return list of edges between provided vertices. Empty list if path not found.
     */
    List<Edge<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges);

}
