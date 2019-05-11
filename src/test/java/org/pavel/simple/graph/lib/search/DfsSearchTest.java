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
import static org.junit.jupiter.api.Assertions.assertThrows;

class DfsSearchTest {

    private final SearchEngine<Vertex> engine = new DfsSearch<>();

    @Test
    void sourceVertexIsNull() {
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge<Vertex>>> verticesToEdges = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> engine.getPath(null, b, verticesToEdges));
    }

    @Test
    void destinationVertexIsNull() {
        Vertex a = Vertex.of("A");
        Map<Vertex, Set<Edge<Vertex>>> verticesToEdges = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> engine.getPath(a, null, verticesToEdges));
    }

    @Test
    void verticesToEdgesIsNull() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");

        assertThrows(IllegalArgumentException.class, () -> engine.getPath(a, b, null));
    }

    @Test
    void noPath() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge<Vertex>>> verticesToEdges = new HashMap<>();

        List<Edge<Vertex>> actual = engine.getPath(a, b, verticesToEdges);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void onlyPathBetweenVertices() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge<Vertex>>> verticesToEdges = new HashMap<>();
        verticesToEdges.put(a, new HashSet<>(Collections.singletonList(Edge.of(a, b))));
        verticesToEdges.put(b, new HashSet<>(Collections.singletonList(Edge.of(b, a))));

        List<Edge<Vertex>> actual = engine.getPath(a, b, verticesToEdges);

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
        Map<Vertex, Set<Edge<Vertex>>> verticesToEdges = new HashMap<>();
        verticesToEdges.put(a, new HashSet<>(Arrays.asList(Edge.of(a, c), Edge.of(a, b))));
        verticesToEdges.put(b, new HashSet<>(Collections.singletonList(Edge.of(b, e))));
        verticesToEdges.put(c, new HashSet<>(Collections.singletonList(Edge.of(c, d))));
        verticesToEdges.put(d, new HashSet<>(Collections.singletonList(Edge.of(d, e))));

        List<Edge<Vertex>> actual = engine.getPath(a, e, verticesToEdges);

        assertEquals(Arrays.asList(Edge.of(a, c), Edge.of(c, d), Edge.of(d, e)), actual);
    }
}