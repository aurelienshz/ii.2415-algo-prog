package edu.isep.algoprog.tp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        print("WELCOME TO AURÉLIEN'S TP No. 3");
        print();
        print();
        print();

        // First, let's try with a graph that has five isolated nodes :
        Graph graph = new Graph(5);
        print("FIVE NODES, NO VERTICES :");
        print(graph.describeAdjacency());
        print();
        print();

        // Graph described by adjacency list, from file :
        try {
            Graph graphFromFile = Graph.createFromFile("./graph.txt");
            print("GRAPH BUILT FROM FILE (graph.txt) / described by its adjacency list :");
            print(graphFromFile.describe());
            print();
            print();
        } catch (IOException e) {
            print("Could not read input file");
            e.printStackTrace();
        }


        // Graph described by its adjacency matrix, from file :
        try {
            print("GRAPH BUILT FROM FILE (graph.txt) / described by its adjacency matrix :");
            AdjacencyMatrixGraph adjacencyMatrixGraph = AdjacencyMatrixGraph.createFromFile("./graph.txt");
            print(adjacencyMatrixGraph.describe());
            print();
            print();
        } catch (IOException e) {
            print("Could not read input file");
            e.printStackTrace();
        }

        // describeFromKbdInput(); // uncomment to describe a graph by inputting its links manually

        print();
        print();
        print();
        print("Karate club :");

        try {
            Graph karateGraph = Graph.createFromFile("./karate.txt");
            print(karateGraph.describe());
            List<Map.Entry<Integer, Integer>> nodesSortedByDegree = karateGraph.getNodesSortedByDegree();
            Map.Entry<Integer, Integer> first = nodesSortedByDegree.get(0);
            Map.Entry<Integer, Integer> second = nodesSortedByDegree.get(1);

            print("Let's find the two highest degree nodes :");
            print("Node " + first.getKey() + " has a degree of " + first.getValue());
            print("Node " + second.getKey() + " has a degree of " + second.getValue());

            print("Therefore, we can assert the two most influential people in this club are those bearing");
            print("the numbers " + first.getKey() + " and " + second.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void describeFromKbdInput() {
        print("Let's describe a graph via keyboard input");
        Scanner sc = new Scanner(System.in);


        String msg = "Create a new edge in the graph (<to> <from>) -- return to stop adding";
        List<String> lines = new ArrayList<>();
        print(msg);
        String in = sc.nextLine();
        while (!in.equals("")) {
            lines.add(in);
            print(msg);
            in = sc.nextLine();
        }

        if (lines.size() > 0) {
            Graph graphFromKbdInput = new Graph(lines);
            print(graphFromKbdInput.describe());
        }
    }

    public static void print() {
        System.out.println();
    }
    public static void print(Object o) {
        System.out.println(o);
    }
}
