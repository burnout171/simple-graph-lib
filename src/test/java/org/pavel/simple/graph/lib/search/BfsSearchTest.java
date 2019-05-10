package org.pavel.simple.graph.lib.search;

import org.junit.jupiter.api.Test;
import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BfsSearchTest {

    private final SearchEngine<Vertex> engine = new BfsSearch<>();

    @Test
    void noPath() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge<Vertex>>> vertexesToEdges = new HashMap<>();

        List<Edge<Vertex>> actual = engine.getPath(a, b, vertexesToEdges);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void onlyPathBetweenVertexes() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge<Vertex>>> vertexesToEdges = new HashMap<>();
        vertexesToEdges.put(a, new HashSet<>(Collections.singletonList(Edge.of(a, b))));
        vertexesToEdges.put(b, new HashSet<>(Collections.singletonList(Edge.of(b, a))));

        List<Edge<Vertex>> actual = engine.getPath(a, b, vertexesToEdges);

        assertEquals(Collections.singletonList(Edge.of(a, b)), actual);
    }


    //      A
    //    /   \
    //   B     C
    //    \     \
    //     \     D
    //      \     \
    //       ------E
    @Test
    void pathInComplexGraph() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Vertex c = Vertex.of("C");
        Vertex d = Vertex.of("D");
        Vertex e = Vertex.of("E");
        Map<Vertex, Set<Edge<Vertex>>> vertexesToEdges = new HashMap<>();
        vertexesToEdges.put(a, new HashSet<>(Arrays.asList(Edge.of(a, c), Edge.of(a, b))));
        vertexesToEdges.put(b, new HashSet<>(Collections.singletonList(Edge.of(b, e))));
        vertexesToEdges.put(c, new HashSet<>(Collections.singletonList(Edge.of(c, d))));
        vertexesToEdges.put(d, new HashSet<>(Collections.singletonList(Edge.of(d, e))));

        List<Edge<Vertex>> actual = engine.getPath(a, e, vertexesToEdges);

        assertEquals(Arrays.asList(Edge.of(a, b), Edge.of(b, e)), actual);
    }
}