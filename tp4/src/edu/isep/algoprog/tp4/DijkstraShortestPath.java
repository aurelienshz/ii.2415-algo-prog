package edu.isep.algoprog.tp4;

import java.util.Arrays;
import java.util.List;

public class DijkstraShortestPath {
    private WeightedDigraph digraph;

    private int sourceNode;
    private boolean[] marked;
    private Integer[] previous;
    private Double[] distance;

    public DijkstraShortestPath(WeightedDigraph dg) {
        this.digraph = dg;

        int length = dg.getAdjacencyList().length;
        marked = new boolean[length];
        Arrays.fill(marked, false);
        previous = new Integer[length];
        distance = new Double[length];
        Arrays.fill(distance, Double.MAX_VALUE);
    }

    public void setSourceNode(int s) {
        this.sourceNode = s;
        distance[s] = 0D;
    }

    public boolean checkNonNegative() {
        List<DirectedEdge>[] adjacencyList = digraph.getAdjacencyList();
        for (List<DirectedEdge> edges: adjacencyList) {
            if (edges == null) continue;

            for (DirectedEdge edge: edges) {
                if (edge.weight() < 0) return false;
            }
        }

        return true;
    }

    public boolean hasPathTo(int nodeToGo) {
        return true; // TODO
    }

    public Double distTo(int nodeToGo) {
        if (!hasPathTo(nodeToGo)) return null;

        return 0D; // TODO
    }

    public String printShortestPath(int nodeToGo) {
        return ""; // TODO
    }
}
