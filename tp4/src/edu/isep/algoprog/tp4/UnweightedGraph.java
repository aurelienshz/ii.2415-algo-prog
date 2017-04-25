package edu.isep.algoprog.tp4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In this implementation, we consider all nodes ranging from zero to the max index found in the descriptive file
 * We keep isolated nodes for more flexibility
 */
public class UnweightedGraph {
    private List<Integer>[] adj;

    private boolean directed;

    public static UnweightedGraph createFromFile(String filePath, boolean directed) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        return new UnweightedGraph(lines, directed);
    }

    public UnweightedGraph(List<String> edgesDescription, boolean directed) {
        this.directed = directed;

        // Find the max node index required
        int length = requiredNodeListLength(edgesDescription);
        adj = new ArrayList[length];
        addEdgesToGraph(edgesDescription);
    }

    public List<Integer>[] getAdjacencyList() {
        return adj;
    }

    private void addEdgesToGraph(List<String> lines) {
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0]);
            int node2Index = Integer.parseInt(nodesNumber[1]);
            addEdgeToNode(node1Index, node2Index);
            if (!directed) addEdgeToNode(node2Index, node1Index);
        }
    }

    private void addEdgeToNode(int node1Index, int node2Index) {
        List<Integer> edges = adj[node1Index];

        if (edges == null) {
            edges = new ArrayList<>();
        }
        edges.add(node2Index);
        adj[node1Index] = edges;
    }

    public List<Integer> getNeighbours(int nodeIndex) {
        return adj[nodeIndex];
    }

    public static int requiredNodeListLength(List<String> edgesDescriptions) {
        Set<Integer> set = new HashSet<>();
        for (String line : edgesDescriptions) {
            // Parse two integers that define a relationship between two nodes :
            List<Integer> nodesIndexes = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            set.addAll(nodesIndexes);
        }
        return Collections.max(set) + 1;
    }
}
