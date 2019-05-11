package org.pavel.simple.graph.lib.graph;

import org.pavel.simple.graph.lib.model.Edge;

public class DirectedGraph<V> extends AbstractGraph<V> {

    @Override
    public void addEdge(Edge<V> edge) {
        checkVerticesExist(edge);
        super.addEdge(edge);
    }
}
