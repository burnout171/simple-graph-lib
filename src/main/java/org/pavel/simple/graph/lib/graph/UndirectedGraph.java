package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;

public class UndirectedGraph<V> extends AbstractGraph<V> {

    @Override
    public void addEdge(Edge<V> edge) {
        checkVertexesExist(edge);
        super.addEdge(edge);
        Edge<V> reversed = Edge.of(edge.getDst(), edge.getSrc());
        super.addEdge(reversed);
    }
}
