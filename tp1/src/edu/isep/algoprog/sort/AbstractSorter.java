package edu.isep.algoprog.sort;

public abstract class AbstractSorter {
    private int[] data;

    public abstract void sort();

    public AbstractSorter(int[] data) {
        this.data = data;
    }

    protected void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
