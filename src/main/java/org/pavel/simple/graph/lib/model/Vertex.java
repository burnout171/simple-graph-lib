package org.pavel.simple.graph.lib.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vertex {

    private final String name;

    public static Vertex of(String name) {
        return new Vertex(name);
    }
}
