package edu.isep.algoprog.tp4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DijkstraShortestPath {
    private WeightedDigraph digraph;

    private int sourceNode;
    private boolean[] marked;
    private Integer[] previous;
    private Double[] distance;

    public Integer[] getPrevious() {
        return previous;
    }

    public Double[] getDistance() {
        return distance;
    }

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

    public void run() throws Exception {
        if (!checkNonNegative()) throw new Exception("Dijkstra's algotithm is impossible on graphs with negative weights");

        while (countMarkedNodes() != countNodes()) { // There are unmarked nodes
            int current = findUnmarkedAtMinimalDistance();
            marked[current] = true;
            List<DirectedEdge> edges = digraph.getAdjacencyList()[current];
            for (DirectedEdge edge: edges) {
                Double potentialShortPath = distance[current] + edge.weight();
                Double currentShortPath = distance[edge.to()];

                if (potentialShortPath < currentShortPath) {
                    distance[edge.to()] = potentialShortPath;
                    previous[edge.to()] = current;
                }
            }
        }

        for (int i  = 0; i < distance.length; i++)
            if (distance[i] == Double.MAX_VALUE) distance[i] = null;
    }

    private int countNodes() {
        // Arrays.asList, but mutable
        // See : http://stackoverflow.com/a/15381271
        List<List<DirectedEdge>> adjacencyList = new ArrayList<>(Arrays.asList(digraph.getAdjacencyList()));
        adjacencyList.removeAll(Collections.singleton(null));
        return adjacencyList.size();
    }

    private int countMarkedNodes() {
        int c = 0;
        for (boolean b: marked) {
            if (b) c++;
        }
        return c;
    }

    private int findUnmarkedAtMinimalDistance() {
        int length = digraph.getAdjacencyList().length;

        int minIndex = -1;
        double min = Double.MAX_VALUE;

        // Filter distances, keeping only the one corresponding to unmarked nodes :
        // TODO a better way to do this could be to keep a list of unmarked distances up-to-date ?
        for (int i = 0; i < length; i++) {
            if (!marked[i] && distance[i] < min) {
                minIndex = i;
                min = distance[i];
            }
        }

        return minIndex;
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
        return distance[nodeToGo] != null;
    }

    public Double distTo(int nodeToGo) {
        // This could return distance[nodeToGo] directly, but it's easier to understand
        // this method's contract when it's written like this
        if (!hasPathTo(nodeToGo)) return null;

        return distance[nodeToGo];
    }

    public List<Integer> getShortestPath (int nodeToGo) {
        if (!hasPathTo(nodeToGo)) return null;

        if (nodeToGo == sourceNode) return new ArrayList<>(Collections.singletonList(sourceNode));

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
}
