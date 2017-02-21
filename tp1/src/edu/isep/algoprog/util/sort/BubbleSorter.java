package edu.isep.algoprog.util.sort;

import edu.isep.algoprog.util.ArrayUtils;

public class BubbleSorter extends ArrayUtils {
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
