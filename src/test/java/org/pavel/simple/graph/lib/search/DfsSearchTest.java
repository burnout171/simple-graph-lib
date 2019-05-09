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

class DfsSearchTest {

    private final SearchEngine engine = new DfsSearch();

    @Test
    void noPath() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge>> vertexesToEdges = new HashMap<>();

        List<Edge> actual = engine.getPath(a, b, vertexesToEdges);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void onlyPathBetweenVertexes() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Map<Vertex, Set<Edge>> vertexesToEdges = new HashMap<>();
        vertexesToEdges.put(a, new HashSet<>(Collections.singletonList(Edge.of(a, b))));
        vertexesToEdges.put(b, new HashSet<>(Collections.singletonList(Edge.of(b, a))));

        List<Edge> actual = engine.getPath(a, b, vertexesToEdges);

        assertEquals(Collections.singletonList(Edge.of(a, b)), actual);
    }


    // A - B - C - D
    @Test
    void pathInChainedVertexes() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Vertex c = Vertex.of("C");
        Vertex d = Vertex.of("D");
        Map<Vertex, Set<Edge>> vertexesToEdges = new HashMap<>();
        vertexesToEdges.put(a, new HashSet<>(Collections.singletonList(Edge.of(a, b))));
        vertexesToEdges.put(b, new HashSet<>(Arrays.asList(Edge.of(b, a), Edge.of(b, c))));
        vertexesToEdges.put(c, new HashSet<>(Arrays.asList(Edge.of(c, b), Edge.of(c, d))));
        vertexesToEdges.put(d, new HashSet<>(Collections.singletonList(Edge.of(d, c))));

        List<Edge> actual = engine.getPath(a, d, vertexesToEdges);

        assertEquals(Arrays.asList(Edge.of(a, b), Edge.of(b, c), Edge.of(c, d)), actual);
    }
}