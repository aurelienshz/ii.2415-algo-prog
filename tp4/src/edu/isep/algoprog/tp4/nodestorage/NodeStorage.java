package edu.isep.algoprog.tp4.nodestorage;

public interface NodeStorage {
    public void add(int toAdd);
    public int getAndRemove();
    public boolean isEmpty();
}
