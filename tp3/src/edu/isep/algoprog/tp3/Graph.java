package edu.isep.algoprog.tp3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

import static edu.isep.algoprog.tp3.GraphUtils.computeNumberOfNodesFromLinks;

/**
 * In this implementation, we consider all nodes ranging from zero to the max index found in the descriptive file
 * We keep isolated nodes for more flexibility
 */
public class Graph {
    private int nodeCount;

    // careful : https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createArrays
    private List<Integer>[] adj;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        adj = new ArrayList[nodeCount];
    }

    public Graph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        new Graph(lines);
    }

    public Graph(List<String> edgesDescription) {
        nodeCount = computeNumberOfNodesFromLinks(edgesDescription);
        adj = new ArrayList[nodeCount];
        addEdgesToGraph(edgesDescription);
    }

    private void addEdgesToGraph(List<String> lines) {
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0]);
            int node2Index = Integer.parseInt(nodesNumber[1]);
            addEdgeToNode(node1Index, node2Index);
        }
    }

    public void addEdgeToNode(int node1Index, int node2Index) {
        List<Integer> edges = adj[node1Index]; // what if node1index doesn't exist ?

        if (edges == null) {
            edges = new ArrayList<>();
        }
        edges.add(node2Index);
        adj[node1Index] = edges;
    }

    public List<Integer> getNeighbors(int nodeIndex) {
        Set<Integer> neighborsSet = new HashSet<>();

        IntStream.range(0, adj.length).forEach(i -> {
             if (adj[i] == null) return;

            // edges pointing to this node
            if (adj[i].contains(nodeIndex))
                neighborsSet.add(i);

            // edges taking their source at this node :
            if (i == nodeIndex)
                neighborsSet.addAll(adj[i]);
        });

        // node is not its own neighbor :
        neighborsSet.remove(nodeIndex);
        return new ArrayList<>(neighborsSet);
    }

    public int getDegree(int nodeIndex) {
        int degree = 0;

        for (int i = 0; i < adj.length; i++) {
            if (adj[i] == null)
                continue;

            if (i == nodeIndex) {
                // edges taking their source at this node :
                degree += adj[i].size();
            } else {
                // edges pointing to this node
                degree += Collections.frequency(adj[i], nodeIndex);
            }
        }

        return degree;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public String describeAdjacency() {
        return Arrays.toString(adj);
    }

    public String describe() {
        String ls = System.getProperty("line.separator");
        String desc = ls;

        desc += "Number of nodes : " + getNodeCount() + ls;
        desc += "Adjacency list : " + describeAdjacency() + ls + ls;

        for (int i = 0; i < getNodeCount(); i++) {
            desc += "Neighbors of node " + i + " : " + getNeighbors(i) + ls;
            desc += "Degree of node " + i + " : " + getDegree(i) + ls;
            desc += "----" + ls;
        }

        desc += degreeCharacteristicValues();

        return desc;
    }

    /**
     * Count nodes that actually have vertices or neighbors :
     */
    private int countLinkedNodes() {
        int j = 0;
        for (int i = 0; i < getNodeCount(); i++) {
            int degree = getDegree(i);

            // if node has no neighbors, and its degree is null, let's ignore it :
            if (degree > 0 && getNeighbors(i).size() > 0)
                j++;
        }
        return j;
    }

    public String degreeCharacteristicValues() {
        String ls = System.getProperty("line.separator");
        int max = 0;
        int min = 0;
        int tot = 0;
        // build a list of node degrees :
        for (int i = 0; i < getNodeCount(); i++) {
            int degree = getDegree(i);

            max = degree > max ? degree : max;
            min = degree < min ? degree : min;
            tot += degree;
        }

        double mean = ((double) tot) / countLinkedNodes();

        return "Max degreee : " + max + ls
                + "Min degreee : " + min + ls
                + "Mean degreee : " + mean + ls;
    }
}
