package edu.isep.algoprog.tp3;

import java.util.*;
import java.util.stream.Collectors;

public class GraphUtils {
    public static int computeNumberOfNodesFromLinks(List<String> lines) {
        Set<Integer> set = new HashSet<>();
        for (String line : lines) {
            // Parse two integers that define a relationship between two nodes :
            List<Integer> nodesNumber = Arrays
                    .stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            set.addAll(nodesNumber);
        }
        // n nodes --> list index from 0 to n --> size is n+1
        return Collections.max(set) + 1;
    }
}
