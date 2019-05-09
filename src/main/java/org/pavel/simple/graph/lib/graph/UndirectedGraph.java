package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;

public class UndirectedGraph extends AbstractGraph {

    @Override
    public void addEdge(Edge edge) {
        checkVertexesExist(edge);
        vertexesToEdges.compute(edge.getSrc(), (k, v) -> {
            v.add(edge);
            return v;
        });
        Edge reversed = Edge.of(edge.getDst(), edge.getSrc());
        vertexesToEdges.compute(edge.getDst(), (k, v) -> {
            v.add(reversed);
            return v;
        });
    }

    private void checkVertexesExist(Edge edge) {
        if (!vertexesToEdges.containsKey(edge.getSrc())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getSrc() + " does not exist");
        }
        if (!vertexesToEdges.containsKey(edge.getDst())) {
            throw new IllegalArgumentException("Could not add edge since vertex " + edge.getDst() + " does not exist");
        }
    }
}
