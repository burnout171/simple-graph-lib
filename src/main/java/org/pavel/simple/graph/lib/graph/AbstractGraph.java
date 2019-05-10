package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.search.DfsSearch;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class AbstractGraph<V> implements Graph<V> {

    private final Map<V, Set<Edge<V>>> vertexesToEdges;

    AbstractGraph() {
        this.vertexesToEdges = new ConcurrentHashMap<>();
    }

    @Override
    public void addVertex(V vertex) {
        if (isNull(vertex)) {
            throw new IllegalArgumentException("Vertex should be not null!");
        }
        vertexesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(Edge<V> edge) {
        vertexesToEdges.compute(edge.getSrc(), (k, v) -> {
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
        searchEngine = nonNull(searchEngine) ? searchEngine : new DfsSearch<>();
        return searchEngine.getPath(src, dst, vertexesToEdges);
    }

    void checkVertexesExist(Edge<V> edge) {
        if (!vertexesToEdges.containsKey(edge.getSrc())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getSrc() + " does not exist");
        }
        if (!vertexesToEdges.containsKey(edge.getDst())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getDst() + " does not exist");
        }
    }
}
