package org.pavel.simple.graph.lib.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Edge {

    private final Vertex src;
    private final Vertex dst;

    public static Edge of(Vertex source, Vertex destination) {
        return new Edge(source, destination);
    }
}
