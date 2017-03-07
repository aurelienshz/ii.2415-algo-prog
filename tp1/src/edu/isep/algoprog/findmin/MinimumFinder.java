package edu.isep.algoprog.findmin;

public class MinimumFinder {
    private int[] data;

    public MinimumFinder(int[] data) {
        this.data = data;
    }

    public int findMin() {
        // We assume that values are > 0
        int min = -1;
        for (int i : data) {
            if (i > min) min = i;
        }
        return min;
    }
}
