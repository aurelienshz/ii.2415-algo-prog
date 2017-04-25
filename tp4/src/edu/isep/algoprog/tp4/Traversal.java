package edu.isep.algoprog.tp4;

import edu.isep.algoprog.tp4.nodestorage.NodeQueue;
import edu.isep.algoprog.tp4.nodestorage.NodeStack;
import edu.isep.algoprog.tp4.nodestorage.NodeStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Traversal {
    private UnweightedGraph graph;

    private BFSShortestPath bfsShortestPath;

    public Traversal(UnweightedGraph graph) {
        this.graph = graph;
        bfsShortestPath = new BFSShortestPath(graph);
    }

    public int connectedComponents() {
        List<Integer>[] adj = graph.getAdjacencyList();
        List<Integer> visited = new ArrayList<>();

        int components = 0;

        for(int i = 0; i < adj.length; i++) {
            if (adj[i] == null) continue;

            if (visited.contains(i)) continue;

            List<Integer> dfsVisit = dfs(i);
            visited.addAll(dfsVisit);
            components ++;
        }

        return components;
    }

    public BFSShortestPath getBfsShortestPath() {
        return bfsShortestPath;
    }

    public List<Integer> bfs(int sourceNode) {
        bfsShortestPath.setSourceNode(sourceNode);
        NodeQueue nodeQueue = new NodeQueue();
        return genericSearch(sourceNode, nodeQueue, true);
    }

    public List<Integer> dfs(int sourceNode) {
        NodeStack nodeStack = new NodeStack();
        return genericSearch(sourceNode, nodeStack, false);
    }

    private List<Integer> genericSearch(int sourceNode, NodeStorage nodeStorage, boolean isBfs) {
        List<Integer> visited = new ArrayList<>();

        nodeStorage.add(sourceNode);

        bfsShortestPath.setDistanceFromStartingNode(sourceNode, 0);

        while (!nodeStorage.isEmpty()) {
            int currentVisit = nodeStorage.getAndRemove();

            visited.add(currentVisit);
            List<Integer> neighbours = graph.getNeighbours(currentVisit);

            if (neighbours != null) {

                // the assignment states : "In case of choice, the vertex with the smallest identifier will be chosen".
                // Therefore, we need to visit the neighbors by order of identifier.
                //
                // When doing BFS, we're storing in a queue, we want to queue the neighbors in natural order.
                //
                // When doing DFS we're storing in a stack, we want to push the neighbors in reverse order so we pop the
                // smallest identifier first.
                neighbours.sort(isBfs ? Comparator.naturalOrder() : Comparator.reverseOrder());

                for (int neighbour: neighbours) {

                    if (!visited.contains(neighbour)) {
                        bfsShortestPath.setPrevious(neighbour, currentVisit);
                        nodeStorage.add(neighbour);

                        int distance = bfsShortestPath.getDistanceFromStartingNode(currentVisit) + 1;
                        bfsShortestPath.setDistanceFromStartingNode(neighbour, distance);
                    }
                }
            }
        }

        return visited;
    }

    public boolean isConnected() {
        return connectedComponents() == 1;
    }
}
