package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

public abstract class AbstractSearchEngine<V> implements SearchEngine<V> {

    @Override
    public List<Edge<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges) {
        validate(source, destination, verticesToEdges);
        return doSearch(source, destination, verticesToEdges);
    }

    abstract List<Edge<V>> doSearch(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges);

    private void validate(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges) {
        if (isNull(source) || isNull(destination)) {
            throw new IllegalArgumentException("Source and destination vertices should not be null!");
        }
        if (isNull(verticesToEdges)) {
            throw new IllegalArgumentException("Graph representation should not be null!");
        }
    }
}
