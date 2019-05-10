package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.search.BfsSearch;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class AbstractGraph<V> implements Graph<V> {

    private final Map<V, Set<Edge<V>>> verticesToEdges;

    AbstractGraph() {
        this.verticesToEdges = new ConcurrentHashMap<>();
    }

    @Override
    public void addVertex(V vertex) {
        if (isNull(vertex)) {
            throw new IllegalArgumentException("Vertex should not be null!");
        }
        verticesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(Edge<V> edge) {
        verticesToEdges.compute(edge.getSource(), (k, v) -> {
            Set<Edge<V>> edges = nonNull(v) ? v : new HashSet<>();
            edges.add(edge);
            return edges;
        });
    }

    @Override
    public List<Edge<V>> getPath(V src, V dst, SearchEngine<V> searchEngine) {
        if (isNull(src) || isNull(dst)) {
            throw new IllegalArgumentException("Src and dst vertexes should not be null!");
        }
        searchEngine = nonNull(searchEngine) ? searchEngine : new BfsSearch<>();
        return searchEngine.getPath(src, dst, verticesToEdges);
    }

    void checkVertexesExist(Edge<V> edge) {
        if (!verticesToEdges.containsKey(edge.getSource())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getSource() + " does not exist");
        }
        if (!verticesToEdges.containsKey(edge.getDestination())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getDestination() + " does not exist");
        }
    }
}
