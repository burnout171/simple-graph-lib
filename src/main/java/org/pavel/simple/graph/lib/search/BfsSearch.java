package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static java.util.Objects.isNull;
import static org.pavel.simple.graph.lib.search.PathConstructor.toPath;

public class BfsSearch<V> implements SearchEngine<V> {

    @Override
    public List<Edge<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> vertexesToEdges) {
        Queue<V> traversed = new LinkedList<>();
        Set<V> visited = new HashSet<>();
        Map<V, Edge<V>> predecessors = new HashMap<>();
        visited.add(source);
        traversed.add(source);
        while (!traversed.isEmpty()) {
            V currentVertex = traversed.poll();
            Set<Edge<V>> adjustmentEdges = vertexesToEdges.get(currentVertex);
            if (isNull(adjustmentEdges)) {
                continue;
            }
            for (Edge<V> edge : adjustmentEdges) {
                V to = edge.getDestination();
                if (visited.add(to)) {
                    traversed.add(to);
                    predecessors.put(to, edge);
                    if (to.equals(destination)) {
                        return toPath(source, destination, predecessors);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
