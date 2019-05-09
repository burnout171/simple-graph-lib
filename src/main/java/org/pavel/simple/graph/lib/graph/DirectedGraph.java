package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;

public class DirectedGraph extends AbstractGraph {

    @Override
    public void addEdge(Edge edge) {
        checkVertexesExist(edge);
        super.addEdge(edge);
    }
}
