package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static java.util.Objects.isNull;

public class DfsSearch<V> implements SearchEngine<V> {

    @Override
    public List<Edge<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> vertexesToEdges) {
        Stack<V> traversed = new Stack<>();
        Set<V> visited = new HashSet<>();
        Map<V, Edge<V>> predecessors = new HashMap<>();
        visited.add(source);
        traversed.push(source);
        while (!traversed.empty()) {
            V currentVertex = traversed.pop();
            Set<Edge<V>> adjustmentEdges = vertexesToEdges.get(currentVertex);
            if (isNull(adjustmentEdges)) {
                continue;
            }
            for (Edge<V> edge : adjustmentEdges) {
                V to = edge.getDst();
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

    private List<Edge<V>> toPath(V src, V dst, Map<V, Edge<V>> predecessors) {
        List<Edge<V>> path = new ArrayList<>();
        V current = dst;
        while (!current.equals(src)) {
            Edge<V> edge = predecessors.get(current);
            path.add(edge);
            current = edge.getSrc();
        }
        Collections.reverse(path);
        return path;
    }
}
