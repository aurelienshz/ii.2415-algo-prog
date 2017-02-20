package edu.isep.algoprog.util.sort;

import edu.isep.algoprog.util.ArrayUtils;

public class SelectionSorter extends ArrayUtils {
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
