package org.pavel.simple.graph.lib.model;

import java.util.Objects;

public class Vertex {

    private final String name;

    private Vertex(String name) {
        this.name = name;
    }

    public static Vertex of(String name) {
        return new Vertex(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
