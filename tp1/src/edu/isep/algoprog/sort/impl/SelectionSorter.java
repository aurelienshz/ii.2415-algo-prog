package edu.isep.algoprog.sort.impl;

import edu.isep.algoprog.sort.AbstractSorter;

public class SelectionSorter extends AbstractSorter {
    private int[] data;

    public SelectionSorter(int[] data) {
        super(data);
        this.data = data;
    }

    private int findMinIndex(int begin, int end) {
        int res = begin;
        for (int i = begin + 1; i != end; ++i) {
            if (data[i] < data[res]) {
                res = i;
            }
        }
        return res;
    }

    public void sort() {
        if (data.length < 2) {
            return;
        }
        for (int i = 0; i != data.length - 1; ++i) {
            swap(i, findMinIndex(i, data.length));
        }
    }
}
