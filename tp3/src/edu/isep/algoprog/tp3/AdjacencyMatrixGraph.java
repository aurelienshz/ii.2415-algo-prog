package edu.isep.algoprog.tp3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static edu.isep.algoprog.tp3.GraphUtils.computeNumberOfNodes;
import static edu.isep.algoprog.tp3.GraphUtils.computeRequiredNodeListLength;

public class AdjacencyMatrixGraph {
    private int nodeCount;
    private boolean[][] adj;

    public AdjacencyMatrixGraph(int nodeCount) {
        this.nodeCount = nodeCount;
        adj = new boolean[nodeCount][nodeCount];
    }

    public static AdjacencyMatrixGraph createFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        return new AdjacencyMatrixGraph(lines);
    }

    public AdjacencyMatrixGraph(List<String> edgesDescription) {
        nodeCount = computeNumberOfNodes(edgesDescription);
        int listLength = computeRequiredNodeListLength(edgesDescription);
        adj = new boolean[listLength][listLength];

        for (boolean[] line: adj) {
            Arrays.fill(line, false);
        }

        addEdgesToGraph(edgesDescription);
    }

    public void addEdgesToGraph(List<String> edgesDescription) {
        for (String line : edgesDescription) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0]);
            int node2Index = Integer.parseInt(nodesNumber[1]);
            addEdgeToNode(node1Index, node2Index);
        }
    }

    private void addEdgeToNode(int node1Index, int node2Index) {
        this.adj[node1Index][node2Index] = true;
    }

    public String describe() {
        String ls = System.getProperty("line.separator");
        String desc = ls;

        desc += "Number of nodes : " + nodeCount + ls;

        desc += "Adjacency matrix : " + describeAdjacency() + ls + ls;

        return desc;
    }

    private String describeAdjacency() {
        String ls = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();

        for (boolean[] l: adj) {
            sb.append(Arrays.toString(l));
            sb.append(ls);
        }

        return sb.toString();
    }
}
