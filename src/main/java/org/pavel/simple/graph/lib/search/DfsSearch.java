package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static java.util.Objects.isNull;

public class DfsSearch implements SearchEngine {

    @Override
    public List<Edge> getPath(Vertex source, Vertex destination, Map<Vertex, Set<Edge>> vertexesToEdges) {
        Stack<Vertex> traversed = new Stack<>();
        Set<Vertex> visited = new HashSet<>();
        Map<Vertex, Edge> predecessors = new HashMap<>();
        visited.add(source);
        traversed.push(source);
        while (!traversed.empty()) {
            Vertex currentVertex = traversed.pop();
            Set<Edge> adjustmentEdges = vertexesToEdges.get(currentVertex);
            if (isNull(adjustmentEdges)) {
                continue;
            }
            for (Edge edge : adjustmentEdges) {
                Vertex to = edge.getDst();
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

    private List<Edge> toPath(Vertex src, Vertex dst, Map<Vertex, Edge> predecessors) {
        List<Edge> path = new ArrayList<>();
        Vertex current = dst;
        while (!current.equals(src)) {
            Edge edge = predecessors.get(current);
            path.add(edge);
            current = edge.getSrc();
        }
        Collections.reverse(path);
        return path;
    }
}
