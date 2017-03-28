package edu.isep.algoprog.tp3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static edu.isep.algoprog.tp3.GraphUtils.computeNumberOfNodesFromLinks;

public class AdjacencyMatrixGraph {
    private int nodeCount;
    private boolean[][] adj;

    public AdjacencyMatrixGraph(int nodeCount) {
        this.nodeCount = nodeCount;
        adj = new boolean[nodeCount][nodeCount];
    }

    public AdjacencyMatrixGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        new AdjacencyMatrixGraph(lines);
    }

    public AdjacencyMatrixGraph(List<String> edgesDescription) {
        nodeCount = computeNumberOfNodesFromLinks(edgesDescription);
        addEdgesToGraph(edgesDescription);
    }

    private void addEdgesToGraph(List<String> edgesDescription) {

    }
}
