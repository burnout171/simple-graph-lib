package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.List;

public interface Graph {

    void addVertex(Vertex vertex);

    void addEdge(Edge edge);

    List<Edge> getPath(Vertex source, Vertex destination, SearchEngine searchEngine);
}
