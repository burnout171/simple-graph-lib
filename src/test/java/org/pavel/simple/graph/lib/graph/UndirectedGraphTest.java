package org.pavel.simple.graph.lib.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;
import org.pavel.simple.graph.lib.search.BfsSearch;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UndirectedGraphTest {

    private final SearchEngine<Vertex> bfs = new BfsSearch<>();

    private Graph<Vertex> graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph<>();
    }

    @Test
    void addNullVertexException() {
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(null));
    }

    @Test
    void addEdgeFromNotExistingVertexException() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(b);
        Edge<Vertex> edge = Edge.of(a, b);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge));
    }

    @Test
    void addEdgeToNotExistingVertexException() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        Edge<Vertex> edge = Edge.of(a, b);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge));
    }

    @Test
    void noPathFound() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);

        List<Edge<Vertex>> actual = graph.getPath(a, b, bfs);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void onlyPathBetweenVertices() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(Edge.of(a, b));

        List<Edge<Vertex>> actual = graph.getPath(a, b, bfs);

        assertEquals(Collections.singletonList(Edge.of(a, b)), actual);
    }

    @Test
    void onlyPathBetweenVerticesWithNullSearchEngine() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(Edge.of(a, b));

        List<Edge<Vertex>> actual = graph.getPath(a, b, null);

        assertEquals(Collections.singletonList(Edge.of(a, b)), actual);
    }

    @Test
    void reversedPathBetweenVertices() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(Edge.of(a, b));

        List<Edge<Vertex>> actual = graph.getPath(b, a, bfs);

        assertEquals(Collections.singletonList(Edge.of(b, a)), actual);
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
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addEdge(Edge.of(a, c));
        graph.addEdge(Edge.of(a, b));
        graph.addEdge(Edge.of(c, d));
        graph.addEdge(Edge.of(d, e));
        graph.addEdge(Edge.of(b, e));

        List<Edge<Vertex>> actual = graph.getPath(a, e, bfs);

        assertEquals(Arrays.asList(Edge.of(a, b), Edge.of(b, e)), actual);
    }
}