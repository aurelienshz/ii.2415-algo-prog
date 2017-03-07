package edu.isep.algoprog.search;

public class Dichotomy {
    private int[] data;

    public Dichotomy(int nbVals) {
        // Generate data :
        int[] data = new int[nbVals];
        for (int i = 0; i != data.length; ++i) {
            data[i] = 2 * i;
        }
        this.data = data;
    }

    public int findIndex(int v) {
        int res = lowerBoundTCO(v);
        if ((res == data.length) || (data[res] != v)) {
            res = -1;
        }
        return res;
    }

    // index of first element >= v
    private int lowerBound(int v) {
        return lowerBound(v, 0, data.length);
    }

    private int lowerBound(int searched, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int pivot = (begin + end) / 2;
        return data[pivot] < searched ? lowerBound(searched, pivot + 1, end) : lowerBound(searched, begin, pivot);
    }

    public int lowerBoundTCO(int searched) {
        int begin = 0, end = data.length;
        while (begin != end) {
            int m = (begin + end) / 2;
            if (data[m] < searched) {
                begin = m + 1;
            } else {
                end = m;
            }
        }
        return begin;
    }
}

