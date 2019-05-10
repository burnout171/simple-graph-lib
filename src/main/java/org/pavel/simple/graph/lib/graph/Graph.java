package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.List;

public interface Graph<V> {

    void addVertex(V vertex);

    void addEdge(Edge<V> edge);

    List<Edge<V>> getPath(V source, V destination, SearchEngine<V> searchEngine);
}
