package edu.isep.algoprog.util.search;

public class Dichotomy {
    private int[] data;

    public Dichotomy(int nbVals) {
        int[] data = new int[nbVals];
        for (int i = 0; i != data.length; ++i) {
            data[i] = 2 * i;
        }
        this.data = data;
    }

    public int find(int elementToFind) {
        System.out.println(elementToFind);
        return lowerBound(elementToFind);
    }

    public int indexOfOrdered(int v) {
        int res = lowerBound(v);
        if ((res == data.length) || (data[res] != v)) {
            res = -1;
        }
        return res;
    }

    // index of first element >= v
    public int lowerBound(int v) {
        return lowerBound(v, 0, data.length);
    }

    public int lowerBound(int v, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int m = (begin + end) / 2;
        return data[m] < v ? lowerBound(v, m + 1, end) : lowerBound(v, begin, m);
    }

    public int lowerBoundTCO(int v) {
        int begin = 0, end = data.length;
        while (begin != end) {
            int m = (begin + end) / 2;
            if (data[m] < v) {
                begin = m + 1;
            } else {
                end = m;
            }
        }
        return begin;
    }
}

