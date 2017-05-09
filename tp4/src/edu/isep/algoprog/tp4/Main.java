package edu.isep.algoprog.tp4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // UnweightedGraph described by adjacency list, from file :
        try {
            System.out.println("######################################");
            System.out.println("### Unweighted graph | BFS and DFS ###");
            System.out.println("######################################");
            System.out.println();
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

            System.out.println("Application of shortest paths algorithms on graph-BFS-SP.txt...");
            System.out.println();
            System.out.println("Interesting uses of shortest paths algorithms...");
            shortestPathsApplication(graphSP);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        }

        try {
            WeightedDigraph graph = WeightedDigraph.createFromFile("graph-WDG.txt");
            DijkstraShortestPath dsp = new DijkstraShortestPath(graph);
            dsp.setSourceNode(1);
            dsp.run();

            Double[] distance = dsp.getDistance();
            for (int i = 0; i < distance.length; i++) {
                if (distance[i] == null) continue;
                System.out.println("Shortest distance from 1 to " + i + " : " + distance[i]);
                System.out.print("Path to node is : "); System.out.println(dsp.getShortestPath(i));
            }
        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testDfs(UnweightedGraph graph) {
        // those functions are interesting when making the graph unconnected (for example delete the "2 5" line in the description file and get 2 components and different traversals)
        System.out.println("Running DFS...");

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
        System.out.println("Running BFS...");

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

        Integer[] eccentricity = new Integer[graph.getAdjacencyList().length];

        for (int i = 0; i < graph.getAdjacencyList().length; i++) {
            List<Integer> p = graph.getAdjacencyList()[i];
            if (p == null) continue;

            traversal.bfs(i);
            List<Integer> distancesList = Arrays.asList(traversal.getBfsShortestPath().getDistanceArray());
            eccentricity[i] = Collections.max(distancesList);
        }

        Integer diameter = Collections.max(Arrays.asList(eccentricity));
        Integer radius = Collections.min(Arrays.asList(eccentricity));

        System.out.println("Eccentricity of each node :");
        for (int i = 0; i < graph.getAdjacencyList().length; i++)
            System.out.println("e(" + i + ") = " + eccentricity[i]);

        System.out.println("Diameter : " + diameter);
        System.out.println("Radius : " + radius);
//        Integer[] distanceArray = traversal.getBfsShortestPath().getDistanceArray();
//        for (int i = 0; i < distanceArray.length; i++) {
//            System.out.println(i + " : " + distanceArray[i]);
//        }

        // TODO excentricity of each vertex, diameter of the graph


    }
}
