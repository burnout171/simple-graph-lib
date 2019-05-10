package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.List;

/**
 * Graph interface provides methods to build a graph and to find a path on it.
 *
 * @param <V> vertex type.
 */
public interface Graph<V> {

    /**
     * Add vertex to the graph.
     * @param vertex to be added.
     * @throws IllegalArgumentException if vertex is null.
     */
    void addVertex(V vertex);

    /**
     * Add edge between 2 vertices to the graph.
     * @param edge to be added.
     * @throws IllegalArgumentException if one of the vertices is not present in the graph.
     */
    void addEdge(Edge<V> edge);

    /**
     * Get path from source to the destination vertex with a provided search engine. If search engine
     * is not provided will use Bfs algorithm. Returns empty collection in case path not found.
     * @param source vertex.
     * @param destination vertex.
     * @param searchEngine path search algorithm implementation.
     * @throws IllegalArgumentException if one of the vertices is null.
     * @return list of edges between provided vertices. Empty list if path not found.
     */
    List<Edge<V>> getPath(V source, V destination, SearchEngine<V> searchEngine);
}
