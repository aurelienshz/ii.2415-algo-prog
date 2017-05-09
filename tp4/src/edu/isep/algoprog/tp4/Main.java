package edu.isep.algoprog.tp4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // UnweightedGraph described by adjacency list, from file :
        try {
            UnweightedGraph graph = UnweightedGraph.createFromFile("graph-DFS-BFS.txt", false);
            testDfs(graph);
            System.out.println();
            testBfs(graph);
        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        }

        // UnweightedGraph described by adjacency list, from file :
        try {
            UnweightedGraph graphSP = UnweightedGraph.createFromFile("graph-BFS-SP.txt", false);
            shortestPathsApplication(graphSP);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        }

        try {
            WeightedDigraph graph = WeightedDigraph.createFromFile("graph-WDG.txt");
            List<DirectedEdge>[] adjacencyList = graph.getAdjacencyList();


            // <describing a weighted digraph>
            for (int i = 0; i < adjacencyList.length; i++) {
                List<DirectedEdge> edges = adjacencyList[i];
                System.out.println(i + " : ");
                if (edges != null)
                    edges.forEach(e -> System.out.println(e.to() + " | " + e.weight()));
            }
            // </describing a weighted digraph>


        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        }
    }

    private static void testDfs(UnweightedGraph graph) {
        // TODO add to comments that those functions are interesting when making the graph unconnected (for example delete the "2 5" line in the description file and get 2 components and different traversals)

        Traversal traversal = new Traversal(graph);

        List<Integer> traverseOrder = traversal.dfs(1);
        System.out.format("DFS visiting order, starting from node 1 : %s", traverseOrder);
        System.out.println();

        traverseOrder = traversal.dfs(5);
        System.out.format("DFS visiting order, starting from node 5 : %s", traverseOrder);
        System.out.println();

        System.out.format("Number of connected components in the graph : %s", traversal.connectedComponents());
        System.out.println();

        System.out.format("The graph is%s connected", traversal.isConnected() ? "" : " not");
        System.out.println();
    }

    public static void testBfs(UnweightedGraph graph) {
        Traversal traversal = new Traversal(graph);

        List<Integer> traverseOrder = traversal.bfs(1);
        System.out.println("BFS visiting order, starting from node 1 :");
        System.out.println(traverseOrder);

        traversal = new Traversal(graph);
        traverseOrder = traversal.bfs(5);
        System.out.println("BFS visiting order, starting from node 5 :");
        System.out.println(traverseOrder);

        BFSShortestPath shortestPath = traversal.getBfsShortestPath();

        System.out.println("Shortest path from 5 to 4");
        System.out.println(shortestPath.getShortestPath(4));
        System.out.println();
    }

    public static void shortestPathsApplication(UnweightedGraph graph) {
        Traversal traversal = new Traversal(graph);
        traversal.bfs(5);

        Integer[] pouet = traversal.getBfsShortestPath().getDistanceArray();
        for (int i = 0; i < pouet.length; i++) {
            System.out.println(i + " : " + pouet[i]);
        }

        // TODO excentricity of each vertex, diameter of the graph


    }
}
