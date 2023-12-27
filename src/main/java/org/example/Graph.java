package org.example;

import java.util.*;

public class Graph {
    final private Map<Vertex, Map<Vertex, Edge>> vertices;
    final private Map<Edge, Pair<Vertex>> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashMap<>();
    }

    public boolean addVertex(Vertex v) {
        if (containsVertex(v)) return false;
        vertices.put(v, new HashMap<>());
        return true;
    }

    public boolean addEdge(Edge edge, Vertex v1, Vertex v2) {
        if (!containsVertex(v1) || !containsVertex(v2)) return false;
        if (findEdge(v1, v2) != null) return false;

        Pair<Vertex> pair = new Pair<>(v1, v2);
        edges.put(edge, pair);
        vertices.get(v1).put(v2, edge);
        vertices.get(v2).put(v1, edge);

        return true;
    }

    public boolean containsVertex(Vertex v) {
        return vertices.containsKey(v);
    }

    public Edge findEdge(Vertex v1, Vertex v2) {
        if (!containsVertex(v1) || !containsVertex(v2))
            return null;
        return vertices.get(v1).get(v2);
    }

    public Collection<Vertex> getNeighbors(Vertex v) {
        if (!containsVertex(v)) return null;
        return vertices.get(v).keySet();
    }

    public Set<Edge> getEdges() {
        return edges.keySet();
    }

    public Set<Vertex> getVertices() {
        return vertices.keySet();
    }

    public Pair<Vertex> getEndpoints(Edge e) {
        return edges.get(e);
    }
}
