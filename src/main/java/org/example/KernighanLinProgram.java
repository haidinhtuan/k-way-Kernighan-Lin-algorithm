package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KernighanLinProgram {
    public static void main(String[] args) {
        for (int fileNum = 100; fileNum <= 1000; fileNum += 100) {
            String fileName = "C:\\Users\\haidi\\IdeaProjects\\test\\src\\main\\java\\org\\example\\graph" + fileNum + ".json";
            for (int k = 10; k <= 50; k += 10) {
                processGraph(fileName, k);
            }
        }
    }

    private static void processGraph(String fileName, int k) {
        System.out.printf("Processing file: %s with k = %d%n", fileName, k);
        File file = new File(fileName);
        try {
            Graph graph = graphFromJson(file);
            List<Set<Vertex>> groups = new ArrayList<>();
            groups.add(new HashSet<>(graph.getVertices()));

            // Start measuring total time in nanoseconds
            long totalStartTime = System.nanoTime();

            while (groups.size() < k) {
                Set<Vertex> groupToSplit = selectGroupToSplit(groups);
                if (groupToSplit == null) {
                    System.out.println("Cannot split further!");
                    break;
                }

                Graph subGraph = createSubGraph(graph, groupToSplit);
                KernighanLin kSub = KernighanLin.process(subGraph);
                updateGroups(groups, groupToSplit, kSub);
            }

            // End measuring total time
            long totalEndTime = System.nanoTime();

            // Calculate total time taken and convert to milliseconds with decimal precision
            double totalDurationMillis = (totalEndTime - totalStartTime) / 1_000_000.0;
            System.out.printf("Total time taken to compute %d groups: %.3f milliseconds.%n", k, totalDurationMillis);

            printGroups(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph graphFromJson(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        JSONTokener tokener = new JSONTokener(fileReader);
        JSONObject root = new JSONObject(tokener);
        JSONArray nodes = root.getJSONArray("nodes");

        Graph graph = new Graph();
        HashMap<String, Vertex> vertexMap = new HashMap<>();

        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            String id = node.getString("id");
            Vertex v = new Vertex(id);
            graph.addVertex(v);
            vertexMap.put(id, v);
        }

        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            JSONArray neighbors = node.getJSONArray("neighbors");
            Vertex sourceVertex = vertexMap.get(node.getString("id"));

            for (int j = 0; j < neighbors.length(); j++) {
                JSONObject neighbor = neighbors.getJSONObject(j);
                Vertex targetVertex = vertexMap.get(neighbor.getString("id"));
                double weight = neighbor.getDouble("weight");
                graph.addEdge(new Edge(weight), sourceVertex, targetVertex);
            }
        }

        fileReader.close();
        return graph;
    }

    private static Set<Vertex> selectGroupToSplit(List<Set<Vertex>> groups) {
        return groups.stream()
                .max(Comparator.comparingInt(Set::size))
                .orElse(null);
    }

    private static Graph createSubGraph(Graph originalGraph, Set<Vertex> vertices) {
        Graph subGraph = new Graph();
        for (Vertex v : vertices) {
            subGraph.addVertex(v);
            for (Vertex neighbor : originalGraph.getNeighbors(v)) {
                if (vertices.contains(neighbor)) {
                    Edge e = originalGraph.findEdge(v, neighbor);
                    subGraph.addEdge(e, v, neighbor);
                }
            }
        }
        return subGraph;
    }

    private static void updateGroups(List<Set<Vertex>> groups, Set<Vertex> originalGroup, KernighanLin kSub) {
        groups.remove(originalGroup);
        groups.add(kSub.getGroupA());
        groups.add(kSub.getGroupB());
    }

    private static void printGroups(List<Set<Vertex>> groups) {
        int groupNum = 1;
        for (Set<Vertex> group : groups) {
            System.out.println("Group " + groupNum++ + ": " + group);
        }
    }
}
