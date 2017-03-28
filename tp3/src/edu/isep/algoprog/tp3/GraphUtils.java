package edu.isep.algoprog.tp3;

import java.util.*;
import java.util.stream.Collectors;

public class GraphUtils {
    public static int computeRequiredNodeListLength(List<String> edgesDescriptions) {
        Set<Integer> set = new HashSet<>();
        for (String line : edgesDescriptions) {
            // Parse two integers that define a relationship between two nodes :
            List<Integer> nodesNumber = Arrays
                    .stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            set.addAll(nodesNumber);
        }
        return Collections.max(set) + 1;
    }

    public static int computeNumberOfNodes(List<String> edgesDescriptions) {
        Set<String> set = new HashSet<>();
        for (String line : edgesDescriptions) {
            // Parse two integers that define a relationship between two nodes :
            List<String> nodesNumber = Arrays
                    .stream(line.split(" "))
                    .collect(Collectors.toList());
            set.addAll(nodesNumber);
        }
        return set.size();
    }
}
