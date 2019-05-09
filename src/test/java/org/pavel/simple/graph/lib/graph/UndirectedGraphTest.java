package org.pavel.simple.graph.lib.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;
import org.pavel.simple.graph.lib.search.DfsSearch;
import org.pavel.simple.graph.lib.search.SearchEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UndirectedGraphTest {

    private final SearchEngine dfs = new DfsSearch();

    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph();
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
        Edge edge = Edge.of(a, b);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge));
    }

    @Test
    void addEdgeToNotExistingVertexException() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        Edge edge = Edge.of(a, b);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge));
    }

    @Test
    void noPathFound() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);

        List<Edge> actual = graph.getPath(a, b, dfs);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void onlyPathBetweenVertexes() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(Edge.of(a, b));

        List<Edge> actual = graph.getPath(a, b, dfs);

        assertEquals(Collections.singletonList(Edge.of(a, b)), actual);
    }

    // A - B
    // |   |
    // D - C
    @Test
    void pathInSquareConnectedVertexes() {
        Vertex a = Vertex.of("A");
        Vertex b = Vertex.of("B");
        Vertex c = Vertex.of("C");
        Vertex d = Vertex.of("D");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addEdge(Edge.of(a, b));
        graph.addEdge(Edge.of(b, c));
        graph.addEdge(Edge.of(c, d));
        graph.addEdge(Edge.of(d, a));

        List<Edge> actual = graph.getPath(a, c, dfs);

        assertEquals(Arrays.asList(Edge.of(a, d), Edge.of(d, c)), actual);
    }
}