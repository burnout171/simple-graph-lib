package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class PathConstructor {

    private PathConstructor() {
    }

    static <V> List<Edge<V>> toPath(V src, V dst, Map<V, Edge<V>> predecessors) {
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
