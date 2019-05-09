package org.pavel.simple.graph.lib.search;

import org.pavel.simple.graph.lib.model.Edge;
import org.pavel.simple.graph.lib.model.Vertex;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchEngine {

    List<Edge> getPath(Vertex src, Vertex dst, Map<Vertex, Set<Edge>> vertexesToEdges);

}
