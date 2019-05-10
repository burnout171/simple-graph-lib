package org.pavel.simple.graph.lib.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Edge<V> {

    private final V src;
    private final V dst;

    public static <V> Edge<V> of(V source, V destination) {
        return new Edge<>(source, destination);
    }
}
