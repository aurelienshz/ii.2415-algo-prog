package edu.isep.algoprog.tp4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WeightedDigraph {
    private List<DirectedEdge>[] adjacencyList;

    public List<DirectedEdge>[] getAdjacencyList() {
        return adjacencyList;
    }

    public static WeightedDigraph createFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        return new WeightedDigraph(lines);
    }

    public WeightedDigraph(List<String> edgesDescription) {
        // Find the max node index required
        int length = requiredNodeListLength(edgesDescription);
        adjacencyList = new ArrayList[length];
        addEdgesToGraph(edgesDescription);
    }

    private void addEdgesToGraph(List<String> edgesDescription) {
        for (String line : edgesDescription) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0]);
            int node2Index = Integer.parseInt(nodesNumber[1]);
            double weight = Double.parseDouble(nodesNumber[2]);
            addEdgeToNode(node1Index, node2Index, weight);
        }
    }

    private void addEdgeToNode(int node1Index, int node2Index, double weight) {
        List<DirectedEdge> edges = adjacencyList[node1Index];
        if (edges == null) {
            edges = new ArrayList<>();
        }

        DirectedEdge de = new DirectedEdge(node1Index, node2Index, weight);

        edges.add(de);
        adjacencyList[node1Index] = edges;
    }

    public static int requiredNodeListLength(List<String> edgesDescriptions) {
        Set<Integer> set = new HashSet<>();
        for (String line : edgesDescriptions) {
            // Parse two integers that define a relationship between two nodes :
            String[] nodes = line.split(" ");
            set.add(Integer.parseInt(nodes[0]));
            set.add(Integer.parseInt(nodes[1]));
        }
        return Collections.max(set) + 1;
    }

    public void describe() {
        for (int i = 0; i < adjacencyList.length; i++) {
            List<DirectedEdge> edges = adjacencyList[i];
            System.out.println(i + " : ");
            if (edges != null)
                edges.forEach(e -> System.out.println(e.to() + " | " + e.weight()));
        }
    }
}
