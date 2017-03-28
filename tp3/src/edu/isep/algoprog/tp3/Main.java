package edu.isep.algoprog.tp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // First, let's try with a graph that has five isolated nodes :
        Graph graph = new Graph(5);
        System.out.println("FIVE NODES, NO VERTICES :");
        System.out.println(graph.describeAdjacency());
        System.out.println();
        System.out.println();

        try {
            Graph graphFromFile = new Graph("./graph.txt");
            System.out.println("GRAPH BUILT FROM FILE (graph.txt)");
            System.out.println(graphFromFile.describe());
            System.out.println();
            System.out.println();
        } catch (IOException e) {
            System.out.println("Could not read input file");
            e.printStackTrace();
        }

        System.out.println("Let's describe a graph via keyboard input");
        Scanner sc = new Scanner(System.in);

        String msg = "Create a new edge in the graph (<to> <from>) -- return to stop adding";
        List<String> lines = new ArrayList<>();
        System.out.println(msg);
        String in = sc.nextLine();
        while (!in.equals("")) {
            lines.add(in);
            System.out.println(msg);
            in = sc.nextLine();
        }

        Graph graphFromKbdInput = new Graph(lines);
        System.out.println(graphFromKbdInput.describe());
    }
}
