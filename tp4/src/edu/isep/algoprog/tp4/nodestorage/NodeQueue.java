package edu.isep.algoprog.tp4.nodestorage;

import java.util.ArrayList;
import java.util.List;

public class NodeQueue implements NodeStorage {
    private List<Integer> list = new ArrayList<>();

    @Override
    public void add(int toAdd) {
        list.add(toAdd);
    }

    @Override
    public int getAndRemove() {
        int r = list.get(0);
        list.remove(0);
        return r;
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }
}
