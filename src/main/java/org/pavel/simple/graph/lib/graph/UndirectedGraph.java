package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;

public class UndirectedGraph<V> extends AbstractGraph<V> {

    @Override
    public void addEdge(Edge<V> edge) {
        checkVerticesExist(edge);
        super.addEdge(edge);
        Edge<V> reversed = Edge.of(edge.getDestination(), edge.getSource());
        super.addEdge(reversed);
    }
}
