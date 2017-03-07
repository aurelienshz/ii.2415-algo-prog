package edu.isep.algoprog.sort.impl;

import edu.isep.algoprog.sort.AbstractSorter;

public class BubbleSorter extends AbstractSorter {
    private int[] data;

    public BubbleSorter(int[] data) {
        super(data);
        this.data = data;
    }

    public void sort() {
        if (data.length < 2) {
            return;
        }
        boolean hadToSwap = false;
        do {
            hadToSwap = false;
            for (int i = 0; i != data.length - 1; ++i) {
                if (data[i] > data[i + 1]) {
                    swap(i, i + 1);
                    hadToSwap = true;
                }
            }
        } while (hadToSwap);
    }
}
