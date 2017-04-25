package edu.isep.algoprog.tp4.nodestorage;

import java.util.Stack;

public class NodeStack implements NodeStorage {
    private Stack<Integer> nodeStack = new java.util.Stack<>();

    @Override
    public void add(int toAdd) {
        nodeStack.push(toAdd);
    }

    @Override
    public int getAndRemove() {
        return nodeStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return nodeStack.size() == 0;
    }
}
