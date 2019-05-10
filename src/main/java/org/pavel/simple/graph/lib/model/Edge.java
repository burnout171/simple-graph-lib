package org.pavel.simple.graph.lib.model;

import java.util.Objects;

public class Edge<V> {

    private final V source;
    private final V destination;

    private Edge(V source, V destination) {
        this.source = source;
        this.destination = destination;
    }

    public static <V> Edge<V> of(V source, V destination) {
        return new Edge<>(source, destination);
    }

    public V getSource() {
        return source;
    }

    public V getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(source, edge.source) &&
                Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }
}
