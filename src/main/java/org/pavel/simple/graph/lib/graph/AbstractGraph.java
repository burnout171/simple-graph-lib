package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;

public abstract class AbstractGraph implements Graph {

    final Map<Vertex, Set<Edge>> vertexesToEdges;

    AbstractGraph() {
        this.vertexesToEdges = new ConcurrentHashMap<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (isNull(vertex)) {
            throw new IllegalArgumentException("Vertex should be not null!");
        }
        vertexesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public List<Edge> getPath(Vertex src, Vertex dst, SearchEngine searchEngine) {
        if (isNull(src) || isNull(dst)) {
            throw new IllegalArgumentException("Src and dst vertexes should not be null!");
        }
        return searchEngine.getPath(src, dst, vertexesToEdges);
    }
}
