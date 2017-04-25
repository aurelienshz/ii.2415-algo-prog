package edu.isep.algoprog.tp4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BFSShortestPath {
    private int sourceNode;
    private boolean[] marked;
    private Integer[] previous;
    private Integer[] distance;

    public void setSourceNode(int n) {
        this.sourceNode = n;
    }

    public BFSShortestPath(UnweightedGraph graph) {
        int length = graph.getAdjacencyList().length;
        marked = new boolean[length];
        Arrays.fill(marked, false);
        previous = new Integer[length];
        distance = new Integer[length];
    }

    public void setPrevious(int nodeIndex, int previousNodeIndex) {
        previous[nodeIndex] = previousNodeIndex;
    }

    public void setDistanceFromStartingNode(int nodeIndex, int distanceToSet) {
        distance[nodeIndex] = distanceToSet;
    }

    public Integer getDistanceFromStartingNode(int nodeIndex) {
        if (distance[nodeIndex] != null) {
            return distance[nodeIndex];
        }
        return null;
    }

    public boolean hasPathTo(int nodeToGo) {
        return distance[nodeToGo] != null;
    }

    public Integer distTo(int nodeToGo) {
        if (hasPathTo(nodeToGo)) {
            return distance[nodeToGo];
        }

        return null;
    }

    public List<Integer> getShortestPath(int nodeToGo) {
        if (!hasPathTo(nodeToGo)) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        path.add(nodeToGo);

        int current = nodeToGo;
        while (previous[current] != sourceNode) {
            current = previous[current];
            path.add(current);
        }

        path.add(sourceNode);
        Collections.reverse(path);
        return path;
    }

    public Integer[] getDistanceArray() {
        return distance;
    }

    public Integer[] getPreviousArray() {
        return previous;
    }
}
